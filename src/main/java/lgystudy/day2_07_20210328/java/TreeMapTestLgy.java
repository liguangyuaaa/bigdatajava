package lgystudy.day2_07_20210328.java;

import day2_07_20210328.src.com.atguigu.java.TreeMapTest;
import org.junit.Test;

import java.util.*;

public class TreeMapTestLgy {
    //向TreeMap中添加key-value,要求key必须是由同一类创建的对象
    //因为要按照key进行排序：自然排序，定制排序

    //自然排序
    @Test
    public void test1(){
        TreeMap map = new TreeMap();
        UserLgy u1 = new UserLgy("Tom",23);
        UserLgy u2 = new UserLgy("Jerry",32);
        UserLgy u3 = new UserLgy("Jack",20);
        UserLgy u4 = new UserLgy("Ros",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);


        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());

        }

    }

    //定制排序
    @Test
    public void test2(){
        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof UserLgy && o2 instanceof UserLgy){
                    UserLgy u1 = (UserLgy)o1;
                    UserLgy u2 = (UserLgy)o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("输入的类型不匹配");
            }
        });
        UserLgy u1 = new UserLgy("Tom",23);
        UserLgy u2 = new UserLgy("Jerry",32);
        UserLgy u3 = new UserLgy("Jack",20);
        UserLgy u4 = new UserLgy("Ros",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);


        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());

        }
    }
}
