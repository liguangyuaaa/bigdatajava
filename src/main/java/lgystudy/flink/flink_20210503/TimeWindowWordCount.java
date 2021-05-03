package lgystudy.flink.flink_20210503;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * 每隔5秒统计最近10秒单纯出现的次数
 */
public class TimeWindowWordCount {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> inputStream = env.socketTextStream("192.168.1.8",1234);

        inputStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] fields = value.split(",");
                for(String word: fields){
                    out.collect(Tuple2.of(word, 1));
                }

            }
        }).keyBy(0).timeWindow(Time.seconds(10), Time.seconds(5))
                .sum(1)
                .print().setParallelism(1);
        env.execute("word count......");

    }
}
