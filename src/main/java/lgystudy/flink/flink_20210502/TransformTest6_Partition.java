package lgystudy.flink.flink_20210502;

import lgystudy.flink.flink_20210429.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformTest6_Partition {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        //从文件中读取数据
        DataStreamSource<String> inputStream = env.readTextFile("/opt/sensor.txt");
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });
        dataStream.keyBy("id").print("keyBy");
        dataStream.print("input");
        // 1. shuffle
        DataStream<String> shuffleStream = inputStream.shuffle();
        dataStream.global().print("global");
        env.execute();
    }

}
