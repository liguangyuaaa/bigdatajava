package lgystudy.day2_07_20210328.java;


import org.junit.Test;

import java.util.*;

/**
 * 一、Map的实现类的结构：
 * -----Map：双列数据，存储key-value对的数据，---类似于高中的函数：y=f(x)
 * |----------HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
 * |-----------------LinkedHashMap：保证在遍历map元素时，可以按照添加的顺序实现遍历
 * |-----------------------原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素
 *                         对于频繁的遍历操作，此类执行效率高于HashMap
 *            TreeMap：保证按照添加key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
 *                     底层使用红黑树
 *            Hashtable
 */
public class MapTestLgy {
    @Test
    public void test5(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,1234);
        map.put("BB",56);

        //遍历所有的key集：keySet（）
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){

            System.out.println(iterator.next());
        }
        System.out.println();

        Collection values = map.values();
        for(Object obj : values){
            System.out.println(obj);
        }
        System.out.println();
        //遍历所有的key-value
        //方式一：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "------>" + entry.getValue());
        }
        System.out.println();
        //方式二
        Set keySet = map.keySet();
        Iterator iterator2 = keySet.iterator();
        while(iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key + "=====" + value);

        }

    }

    @Test
    public void test4(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45, 123);
        map.put("BB",56);
        System.out.println(map.get(45));
        boolean isExist = map.containsKey("BB");
        System.out.println(isExist);

        isExist = map.containsValue(123);
        System.out.println(isExist);
        map.clear();
        System.out.println(map.isEmpty());
    }

    @Test
    public void test3(){
        Map map = new HashMap();
        //添加
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        //修改
        map.put("AA",87);
        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);

        map.putAll(map1);
        System.out.println(map);

        //remove(Object key)
        Object value = map.remove("CC");
        System.out.println(value);
        System.out.println(map);

        //clear()
        map.clear();//与map = null 操作不同
        System.out.println(map.size());
        System.out.println(map);


    }
    @Test
    public void test2(){
        Map map = new HashMap();
        map = new LinkedHashMap();
        map.put(123,"AA");
        map.put(345,"BB");
        map.put(12,"CC");

        System.out.println(map);
    }


    @Test
    public void test1(){
        Map map = new HashMap();
//        map = new Hashtable();
        map.put(null,123);

    }
}
