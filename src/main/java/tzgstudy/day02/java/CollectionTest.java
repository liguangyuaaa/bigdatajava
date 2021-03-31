package tzgstudy.day02.java;

/*
Collection接口中声明的方法的测试
1.add 添加元素方法
2.contains 方法
3.containsAll 方法 比较的时一个集合中是否被另一个集合包含
4.remove：从集合中移除元素，移除对象元素，并不是索引
5.removeAll：从一个集合中移除本身集合具备的元素
6.retainAll：求两个集合的交集 返回当前集合
7.equals：判断两个集合是否相等，元素一致相等
8.hashCode：求集合对象的hashcode值
9.toArray：将一个集合转换为一个数组
10.Arrays.asList：将一个数组转换为集合
11.Arrays.asList：Arrays.asList（new int[]{1,2}）:此时转换后的集合size为1。因为int为基本类型
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionTest {

    @Test
    public void test1(){

        Collection col = new ArrayList();
        col.add(123);
        col.add(456);

        Person p = new Person("Jerry",20);
        col.add(p);
        col.add(new String("Tom"));
        col.add(false);




        //判断contains(Object obj):判断当前集合中是否包含obj会调用equals方法
        boolean tom = col.contains(new String("Tom"));
        System.out.println(col.contains(new Person("Jerry",20)));
        System.out.println(tom);

        //2.containsAll(Collection col) 判断形参col中的所有元素是否都在当前集合中
        Collection collection = Arrays.asList(123, 456);
        System.out.println(col.containsAll(collection));
    }

    @Test
    public void test2(){
        //3.从当前集合中移除元素

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        coll.remove(new Person("Jerry",20));
        System.out.println(coll);

        //4.移除一个集合中的所有元素
        Collection collection = Arrays.asList(123,4567);

        coll.removeAll(collection);

        System.out.println(coll);
    }

    @Test
    public void test3(){

        Collection coll = new ArrayList();
        coll.add("aini");
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(1);
        coll.add(false);



        //5.retainAll(Collection col)交集：获取当前集合和coll集合的交集，并返回给当前集合
//         Collection collection = Arrays.asList(123, 456);
//         coll.retainAll(collection);
//        System.out.println(coll);

        //6.要想让两个集合相等，必须当前集合和形参集合的元素都相同
        Collection coll1 = new ArrayList();
        coll1.add("aini");
        coll1.add(new Person("Jerry",20));
        coll1.add(new String("Tom"));
        coll1.add(1);
        coll1.add(false);
        System.out.println(coll.equals(coll1));
    }

    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);


        //7.返回当前对象的hashcode值
        System.out.println("coll.hashCode() = " + coll.hashCode());

        //8.集合转为数组toArray（）
        Object[] objects = coll.toArray();
        for (Object object : objects) {
            System.out.println("object = " + object);
        }

        //9.拓展：数组--->集合 调用Arrays类的静态方法asList()
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println("lsit = " + list);
        System.out.println("list.size() = " + list.size());

        //存储的是一个数组对象
        List<int[]> ints = Arrays.asList(new int[]{123, 456});
        System.out.println(ints.size());

        //包装类型 存储的就是单个的包装类对象
        List<Integer> integers = Arrays.asList(123, 456);
        System.out.println(integers.size());
        //iterator() 迭代器
    }
}
