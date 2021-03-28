package lgystudy.day2_06_20210327.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorTestLgy {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new PersonLgy("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();

        //方式二： 不推荐
        for(int i = 0; i < coll.size(); i++){
            //System.out.println(iterator.next());
        }

        // 方式三： 推荐
        // hasNext(): 判断是否还有下一个元素
        while(iterator.hasNext()){
            //next():1.指针下移，2将下移以后集合位置上的元素返回
            System.out.println(iterator.next());
        }
    }

    // 测试Iterator中的remove（）
    //如果还未调用next()或在上一次调用next方法之后已经调用了remove 方法
    //再调用remove都会报IllegalStateException
    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new PersonLgy("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //删除集合中Tom
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();
            }
        }

        //遍历集合
        iterator = coll.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
