package tzgstudy.day02.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 遍历的关键：
 * iterator
 * hasnext（）做判断
 * next（） 读取下一个元素
 */
public class IteratorTest {

    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //
        Iterator iterator = coll.iterator();

        //方式1 遍历：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
        //方式2  不推荐
//        for (int i = 0; i < coll.size(); i++) {
//            System.out.println(iterator.next());
//        }

        //方式三：推荐
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        
    }

    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //错误方式1
        Iterator iterator = coll.iterator();
//        while (iterator.next() != null){
//            System.out.println(iterator.next());
//        }

        //错误方式2
        //集合对象每次调用iterator（）方法得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前
        while (coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }
    }

    //
    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //删除集合中"Tom"
        Iterator iterator = coll.iterator();

        while (iterator.hasNext()){
            //iterator.remove();
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();
             //iterator.remove();
            }
        }

        iterator = coll.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
