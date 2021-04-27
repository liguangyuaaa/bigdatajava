package lgystudy.flink.flink_20210427;


import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;



/**
 * 需求：当接受到的相同key的元素个数等于3个或者超过3个的时候
 *  就计算这些元素的value的平均值
 *  计算 keyed stream 中每个3个元素的value的平均值
 */
public class TestKeyedStateMain {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Tuple2<Long, Long>> dataStreamSource = env.fromElements(Tuple2.of(1L, 3L), Tuple2.of(1L, 5L),
                Tuple2.of(1L, 7L), Tuple2.of(2L, 4L), Tuple2.of(2L, 2L), Tuple2.of(2L, 5L));
        dataStreamSource.keyBy(0)
                .flatMap(new CountWindowAverageWithValueState())
                .print();
        env.execute("testkeyStateMain");
    }
    /**
     * valueState<T>:这个状态为每一个key保存一个值</T>
     *  value（）获取状态值
     *  update（）更新状态值
     *  clear（）清除状态
     *
     */
    public static class CountWindowAverageWithValueState extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Double>> {

        //用以保存每个key出现的次数，以及这个key对应的value的总值
        //managed keyed state
        //1。 ValueState 保存的是对应的一个key 的状态值
        private ValueState<Tuple2<Long, Long>> countAndSum;

        @Override
        public void open(Configuration parameters) throws Exception {
            //注册状态
            ValueStateDescriptor<Tuple2<Long, Long>> descriptor = new ValueStateDescriptor<Tuple2<Long, Long>>("average", Types.TUPLE(Types.LONG, Types.LONG));
            countAndSum = getRuntimeContext().getState(descriptor);

        }

        @Override
        public void flatMap(Tuple2<Long, Long> element, Collector<Tuple2<Long, Double>> out) throws Exception {
            //拿到当前的key的状态值
            Tuple2<Long, Long> currentState = countAndSum.value();

            //如果状态值还没有初始化，则初始化
            if(currentState == null){
                currentState = Tuple2.of(0L, 0L);
            }
            //更新状态值中的元素的个数
            currentState.f0 += 1;

            //更新状态值中的总值
            currentState.f1 += element.f1;

            //更新状态
            countAndSum.update(currentState);

            //判断，如果当前的key出现了3次，则需要计算平均值，并且输出
            if(currentState.f0 >=3){
                double avg = (double)currentState.f1 / currentState.f0;
                //输出key及其对应的平均值
                out.collect(Tuple2.of(element.f0, avg));
                //清空状态值
                countAndSum.clear();
            }

        }
    }
}
