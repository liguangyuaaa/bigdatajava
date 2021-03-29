package tzgstudy.day02.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * List的方法：
 * add：添加元素 两个参数（i，element）在指定位置添加元素
 * addAll：添加整个集合中的元素，也可以指定元素的位置及逆行添加 addAll(int index, Collection eles)
 * get：获取指定索引的数据
 * indexof：获取元素在集合中首次出现的时间
 * lastindexof：获取元素在集合种末次出现的时间
 * get：获取指定索引的元素
 * remove：移除指定位置的元素
 * remove：移除指定元素
 * set：设置指定索引位置的的元素值
 */
public class ListTest {


    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        Iterator iterator = list.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for (Object o : list) {
            System.out.println("o = " + o);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("list.get(i) = " + list.get(i));
        }
    }

    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        //int indexOf(Object obj):返回obj在集合中首次出现的位置。如果不存在，返回-1.
        int i = list.indexOf(4567);
        System.out.println(i);

        //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置。如果不存在，返回-1.
        int i1 = list.lastIndexOf(456);
        System.out.println("i1 = " + i1);

        //Object get(int index):获取指定index位置的元素
        System.out.println(list.get(0));

        //Object remove(int index):移除指定index位置的元素，并返回此元素
        Object remove = list.remove(0);
        System.out.println("remove = " + remove);
        System.out.println(list);

        //Object set(int index, Object ele):设置指定index位置的元素为ele
        list.set(1,"CC");
        System.out.println(list);

        //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的左闭右开区间的子集合

        List list1 = list.subList(2, 4);
        System.out.println("list1 = " + list1);
    }


    @Test
    public void test1(){

        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        System.out.println(list);

        //void add(int index, Object ele):在index位置插入ele元素
        list.add(1,"BB");  //将原来的数据后移
        System.out.println(list);

        //boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
//        list.addAll(list1);
        list.add(list1); //添加的是集合的整体对象
        System.out.println(list.size()); //9 ,7


    }
}
