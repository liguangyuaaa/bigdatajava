package tzgstudy.day02.map;

//java集合中map的使用方法

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo {

    @Test
    public void test1(){
        //创建Map对象
        HashMap<String, String> map = new HashMap<>();

        //给map中添加元素
        map.put("星期一","Monday");
        map.put("星期日","Sunday");

        System.out.println("map = " + map);

        //给Map中添加新元素，会返回key对应的原来的value值，若key没有对应的值，返回null
        System.out.println(map.put("星期一", "Mon"));
        System.out.println(map);

        //根据指定的key获取对应的value
        String 星期日 = map.get("星期日");
        System.out.println("星期日 = " + 星期日);

        //根据key删除指定的key 会返回key对应的value值
        System.out.println(map.remove("星期日"));
        System.out.println(map);

    }

    @Test
    public void test2(){
       //Map集合遍历键找值方式
        //创建Map对象
        Map<String, String> map = new HashMap<String,String>();
        //给map中添加元素
        map.put("邓超", "孙俪");
        map.put("李晨", "范冰冰");
        map.put("刘德华", "柳岩");

        //获取map中的所有key
        Set<String> keySets = map.keySet();

        //遍历存放所有key的set集合
        Iterator<String> iterator = keySets.iterator();

        while (iterator.hasNext()){
            String key = iterator.next();
            //获取map中key对应的值
            String value = map.get(key);
            System.out.println("key = " + value);
        }
    }

    @Test
    public void test3() {
        //Map集合遍历键找值方式
        //创建Map对象
        Map<String, String> map = new HashMap<String, String>();
        //给map中添加元素
        map.put("邓超", "孙俪");
        map.put("李晨", "范冰冰");
        map.put("刘德华", "柳岩");

        //获取map中的所有entryset
        Set<Map.Entry<String, String>> keySets = map.entrySet();

        //遍历存放所有key的set集合
        Iterator<Map.Entry<String, String>> iterator = keySets.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + "=" + next.getValue());
        }
    }
    @Test
    public void test4() {
        //Map集合遍历键找值方式
        //创建Map对象
        Map<String, String> map = new HashMap<String, String>();
        //给map中添加元素
        map.put("邓超", "孙俪");
        map.put("李晨", "范冰冰");
        map.put("刘德华", "柳岩");

        //获取map中的所有entryset
        Set<Map.Entry<String, String>> keySets = map.entrySet();

        for (Map.Entry<String, String> keySet : keySets) {
            System.out.println(keySet.getKey() + "=" + keySet.getValue());
        }
    }
}
