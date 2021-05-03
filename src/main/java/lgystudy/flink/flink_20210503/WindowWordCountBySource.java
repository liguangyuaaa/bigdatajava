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

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class WindowWordCountBySource {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.addSource(new TestSource());
        dataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] fields = value.split(",");
                for (String s : fields) {
                    out.collect(Tuple2.of(s, 1));
                }
            }
        }).keyBy(0)
                .timeWindow(Time.seconds(10), Time.seconds(5))
                .process(new SumProcessFunction()).print().setParallelism(1);
        env.execute("windowWordCountAndTime");
    }
    public static class TestSource implements SourceFunction<String> {

        FastDateFormat dateFormat = FastDateFormat.getInstance("HH:mm:ss");
        @Override
        public void run(SourceContext<String> ctx) throws Exception {
            String currTime = String.valueOf(System.currentTimeMillis());
            System.out.println(currTime);
            //这个操作是为了保证是10S的倍数
            //我们执行的时间是10的倍数
            while(Integer.valueOf(currTime.substring(currTime.length() - 4)) > 100){
                currTime=String.valueOf(System.currentTimeMillis());
                continue;
            }
            System.out.println("开始发送事件的时间："+dateFormat.format(System.currentTimeMillis()));
            TimeUnit.SECONDS.sleep(3);
            ctx.collect("hadoop");
            ctx.collect("hadoop");
            TimeUnit.SECONDS.sleep(3);
            ctx.collect("hadoop");
            TimeUnit.SECONDS.sleep(30000);
        }

        @Override
        public void cancel() {

        }
    }
    public static class SumProcessFunction extends ProcessWindowFunction<Tuple2<String, Integer>,
                Tuple2<String, Integer>, Tuple, TimeWindow> {

        FastDateFormat dateFormat = FastDateFormat.getInstance("HH:mm:ss");


        @Override
        public void process(Tuple tuple, Context context, Iterable<Tuple2<String, Integer>> elements, Collector<Tuple2<String, Integer>> out) throws Exception {
            int count = 0;
            for(Tuple2<String, Integer> e: elements){
                count++;
            }
            out.collect(Tuple2.of(tuple.getField(0), count));

        }
    }























}
