package lgystudy.flink.flink_20210506;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.table.expressions.Or;



public class OrderPayTimeout {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<String> inputStream = env.readTextFile("/opt/study/Data/OrderLog.csv");
        DataStream<OrderEvent> orderEventDataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new OrderEvent(new Long(fields[0]), fields[1], fields[2], new Long(fields[3]));
        })
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<OrderEvent>() {
                    @Override
                    public long extractAscendingTimestamp(OrderEvent element) {
                        return element.getTimestamp() * 1000L;
                    }
                });
        //1. 定义一个带时间限制的模式

    }
}
