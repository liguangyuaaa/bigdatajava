package lgystudy.day2_06_20210327.java;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionTestLgy {
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new PersonLgy("Jerry",20));
        coll.add(false);
        //1. contains(Object obj):判断当前集合中是否包含obj
        //我们在判断会调用obj对象所在类的equals（）；
        boolean contains = coll.contains(123);
        System.out.println(contains);
        System.out.println(coll.contains(new String("Tom")));
        System.out.println(coll.contains(new PersonLgy("Jerry",20)));
        Collection colll = Arrays.asList(123,4567);
        System.out.println(coll.contains(colll));

    }
    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new PersonLgy("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);
        
        Collection colll = new ArrayList();
        colll.add(456);
        colll.add(123);
        colll.add(new PersonLgy("Jerry",20));
        colll.add(new String("Tom"));
        colll.add(false);
        System.out.println(coll.equals(colll));

    }
    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new PersonLgy("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //7 .hashCode():返回当前对象的哈希值
        System.out.println(coll.hashCode());
        //8. 集合   ----->数组：toArray()
        Object[] arr = coll.toArray();
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        //拓展： 数组 --->集合：调用Arrays类的静态方法asList()
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);

        List arr1 = Arrays.asList(new int[]{123, 456});
        System.out.println(arr1.size());

        List arr2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr2.size());


    }
}
