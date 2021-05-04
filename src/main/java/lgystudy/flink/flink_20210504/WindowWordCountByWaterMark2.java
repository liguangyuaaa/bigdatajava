package lgystudy.flink.flink_20210504;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求：3秒一个窗口，把相同 key合并起来
 *
 * hadoop,1461756862000
 * hadoop,1461756866000
 * hadoop,1461756872000
 * hadoop,1461756873000
 * hadoop,1461756874000
 * hadoop,1461756876000
 * hadoop,1461756877000
 *
 *
 * window + watermark  观察窗口是如何被触发？
 *
 * 可以解决乱序问题
 *
 *
 */
public class WindowWordCountByWaterMark2 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<String> dataStreamSource = env.socketTextStream("192.168.1.8", 1234);
        dataStreamSource.map(new MapFunction<String, Tuple2<String, Long>>() {

            @Override
            public Tuple2<String, Long> map(String value) throws Exception {
                String[] fields = value.split(",");
                return new Tuple2<>(fields[0], Long.valueOf(fields[1]));
            }
        }).assignTimestampsAndWatermarks(new EventTimeExtractor())
                .keyBy(0)
                .timeWindow(Time.seconds(3))
                .process(new SumProcessWindowFunction3())
                .print().setParallelism(1);
        env.execute("windowWordCountBy");

    }
    /**
     * IN,out,key,w
     * in:输入的数据类型
     * out：输出的数据类型
     * key：key的数据类型（在flink里面，String用tuple表示）
     * w:window的数据类型
     */

    public static class SumProcessWindowFunction3 extends ProcessWindowFunction<Tuple2<String, Long>, String, Tuple, TimeWindow>{

        FastDateFormat dateFormat = FastDateFormat.getInstance("HH:mm:ss");

        /**
         * 当一个window触发计算的时候会调用这个方法
         * @param tuple key
         * @param context operator的上下文
         * @param elements 指定window的所有元素
         * @param out 用户输出
         * @throws Exception
         */
        @Override
        public void process(Tuple tuple, Context context, Iterable<Tuple2<String, Long>> elements, Collector<String> out) throws Exception {
            System.out.println("处理时间："+ dateFormat.format(context.currentProcessingTime()));
            System.out.println("window start time:" + dateFormat.format(context.window().getStart()));

            List<String> list = new ArrayList<>();
            for(Tuple2<String, Long> ele: elements){
                list.add(ele.toString() + "|" + dateFormat.format(ele.f1));
            }
            out.collect(list.toString());
            System.out.println("window end time: " + dateFormat.format(context.window().getEnd()));

        }
    }
    private static class EventTimeExtractor implements
            AssignerWithPeriodicWatermarks<Tuple2<String, Long>>{
        FastDateFormat dateFormat = FastDateFormat.getInstance("HH:mm:ss");
        //3S 计算时间最大的一个值
        private long currentMaxEventTime = 0L;
        private long maxOutOfOrderness = 10000;//最大

        @Nullable
        @Override
        public Watermark getCurrentWatermark() {
            //watermark的计算的逻辑
            //如何计算出来一个窗口里面的watermark
            return new Watermark(currentMaxEventTime - maxOutOfOrderness);
        }

        @Override
        public long extractTimestamp(Tuple2<String, Long> element, long timeStamp) {
            Long currentElementTime = element.f1;


            currentMaxEventTime = Math.max(currentMaxEventTime,currentElementTime);
//
            System.out.println("event = " + element
                    + "|" + dateFormat.format(element.f1) // Event Time
                    + "|" + dateFormat.format(currentMaxEventTime)  // Max Event Time
                    + "|" + dateFormat.format(getCurrentWatermark().getTimestamp())); // Current Watermark

            return currentElementTime;
        }
    }










































}
