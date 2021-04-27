package lgystudy.flink.flink_20210427;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

public class StreamingSourceFromCollect {
    public static void main(String[] args) throws Exception {
        //1。获取环境变量
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //2。模拟数据
        ArrayList<String> data =new ArrayList<String>();
        data.add("hadoop");
        data.add("flink");
        data.add("spark");
        //3。获取数据源
        DataStreamSource<String> dataStream = env.fromCollection(data);
        //4。transformation操作
        SingleOutputStreamOperator<String> addPreStream = dataStream.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                return "nx_" + s;
            }
        });
        //5。对结果进行处理（打印）
        addPreStream.print().setParallelism(1);
        //6.启动程序
        env.execute("StreamingSourceFromCollection");

    }
}
