package lgystudy.flink.flink_20210503;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;


import java.util.concurrent.TimeUnit;

/**
 * 需求：每隔5秒计算最近10秒的单词次数
 * 乱序
 */
public class WindowWordCountBySource2 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.addSource(new TestSource2());
        dataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] fields = value.split(",");
                for(String word: fields)
                    out.collect(Tuple2.of(word, 1));
            }
        }).keyBy(0)
                .timeWindow(Time.seconds(10), Time.seconds(5))
                .process(new SumProcessFunction()).print().setParallelism(1);
        env.execute("WindowWordCountAndTime");
    }
    public static class TestSource2 implements SourceFunction<String>{
        FastDateFormat dateFormat = FastDateFormat.getInstance("HH:mm:ss");

        @Override
        public void run(SourceContext<String> ctx) throws Exception {
            String currTime = String.valueOf(System.currentTimeMillis());
            while(Integer.valueOf(currTime.substring(currTime.length() - 4)) > 100){
                currTime=String.valueOf(System.currentTimeMillis());
                continue;

            }
            System.out.println("开始发送事件的时间："+dateFormat.format(System.currentTimeMillis()));
            TimeUnit.SECONDS.sleep(3);
            //实际上我们的数据是在13秒的时候生成的，只是19的时候被发送出去。
            String event = "hadoop";
            ctx.collect(event);
            TimeUnit.SECONDS.sleep(3);
            ctx.collect(event);

            TimeUnit.SECONDS.sleep(300990);
        }

        @Override
        public void cancel() {

        }
    }

    public static class SumProcessFunction extends ProcessWindowFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple, TimeWindow> {
        FastDateFormat dateFormat = FastDateFormat.getInstance("HH:mm:ss");

        @Override
        public void process(Tuple tuple, Context context, Iterable<Tuple2<String, Integer>> elements, Collector<Tuple2<String, Integer>> out) throws Exception {
         int count=0;
         for(Tuple2<String, Integer> e: elements)
             count++;
         out.collect(Tuple2.of(tuple.getField(0),count));
        }

    }

}
