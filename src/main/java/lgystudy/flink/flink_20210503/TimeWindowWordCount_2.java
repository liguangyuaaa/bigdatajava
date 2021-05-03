package lgystudy.flink.flink_20210503;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;


public class TimeWindowWordCount_2 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.socketTextStream("192.168.1.8", 1234);
        dataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {

            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] fields = value.split(",");
                for(String s: fields){
                    out.collect(Tuple2.of(s, 1));
                }
            }
        }).keyBy(0).timeWindow(Time.seconds(10), Time.seconds(5))
                .process(new MySumProcessWindowFuntion())
                .print().setParallelism(1);
        env.execute();
    }
    public static class MySumProcessWindowFuntion extends ProcessWindowFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple, TimeWindow> {

        FastDateFormat dataformat = FastDateFormat.getInstance("HH:mm:ss");

        @Override
        public void process(Tuple tuple, Context context, Iterable<Tuple2<String, Integer>> elements,
                            Collector<Tuple2<String, Integer>> out) throws Exception {
            System.out.println("当前系统时间："+dataformat.format(System.currentTimeMillis()));
            System.out.println("窗口处理时间："+dataformat.format(context.currentProcessingTime()));
            System.out.println("窗口开始时间："+dataformat.format(context.window().getStart()));

            int sum=0;
            for(Tuple2<String, Integer> ele: elements){
                sum += 1;
            }
            out.collect(Tuple2.of(tuple.getField(0), sum));
            System.out.println("窗口结束时间："+dataformat.format(context.window().getEnd()));
            System.out.println("=====================");


        }
    }
}
