package lgystudy.flink.flink_20210427;


import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.calcite.shaded.com.google.common.collect.Lists;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Collections;
import java.util.List;


/**
 * 需求：当接收到的相同key的元素个等于3个或者超过3个或超过3个的时候
 *  就计算这些元素的value的平均值
 *  计算keyed stream 中每个3个元素的value的平均值
 */
public class TestKeydStateMainList {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Tuple2<Long, Long>> dataStreamSource = env.fromElements(Tuple2.of(1L, 3L),
                Tuple2.of(1L, 5L), Tuple2.of(1L,7L), Tuple2.of(2L, 4L), Tuple2.of(2L, 2L), Tuple2.of(2L, 5L));
        dataStreamSource.keyBy(0)
                .flatMap(new CountWindowAverageWithValueState())
                .print();
        env.execute("TestStatefulApi");
    }

    /**
     * ListState<T> :这个状态为每一个key保存集合的值</T>
     * get（）获取状态值
     * add（）/addAll（）更新状态值，将数据放到状态中
     * clear（）清除状态
     */
    public static class CountWindowAverageWithValueState extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Double>>{
        //1。ListState保存的是对应的一个key的出现的所有的元素
        private ListState<Tuple2<Long, Long>> elementsByKey;
        @Override
        public void open(Configuration parameters) throws Exception {
            //注册状态
            ListStateDescriptor<Tuple2<Long, Long>> descriptor =
                    new ListStateDescriptor<Tuple2<Long, Long>>(
                            "average", //状态的名字
                            Types.TUPLE(Types.LONG, Types.LONG));//状态存储的数据类型
            elementsByKey = getRuntimeContext().getListState(descriptor);

        }

        @Override
        public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Double>> out) throws Exception {
            //拿到当前key的状态值
            Iterable<Tuple2<Long, Long>> currentState = elementsByKey.get();
            //如果状态值还没有初始化，则初始化
            if(currentState == null){
                elementsByKey.addAll(Collections.emptyList());
            }
            //更新状态
            elementsByKey.add(value);
            //判断，如果当前的key出现了3次，则需要计算平均值，并且输出
            List<Tuple2<Long, Long>> allElements =
                    Lists.newArrayList(elementsByKey.get());
            if(allElements.size() >= 3){
                long count = 0;
                long sum = 0;
                for(Tuple2<Long, Long> ele : allElements){
                    count ++;
                    sum += ele.f1;
                }
                double avg = (double) sum / count;
                out.collect(Tuple2.of(value.f0, avg));
            }
            //清除状态
            elementsByKey.clear();

        }
    }

}
