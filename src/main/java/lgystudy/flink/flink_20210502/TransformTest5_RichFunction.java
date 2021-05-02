package lgystudy.flink.flink_20210502;

import groovy.lang.Tuple;
import lgystudy.flink.flink_20210429.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformTest5_RichFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        //从文件读取数据
        DataStream<String> inputStream = env.readTextFile("/opt/sensor.txt");
        //转化为sensoring类型
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });
        DataStream<Tuple2<String, Integer>> resultStream = dataStream.map(new MyMapper());
        resultStream.print();
        env.execute();
    }
    public static class MyMapper0 implements MapFunction<SensorReading, Tuple2<String, Integer>>{

        @Override
        public Tuple2<String, Integer> map(SensorReading value) throws Exception {
            return new Tuple2<>(value.getId(), value.getId().length());
        }
    }
    //实现自定义富函数
    public static class MyMapper extends RichMapFunction<SensorReading, Tuple2<String, Integer>>{

        @Override
        public Tuple2<String, Integer> map(SensorReading value) throws Exception {
            return new Tuple2<>(value.getId(), getRuntimeContext().getIndexOfThisSubtask());
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            //初始化工作，一般是定义状态，或者建立数据库连接
            System.out.println("open");

        }

        @Override
        public void close() throws Exception {
            //一般是关闭连接和清空状态的收尾操作
            System.out.println("close");
        }
    }



}
