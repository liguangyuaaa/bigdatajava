package lgystudy.day2_06_20210327.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTestLgy {
    /**
     * 1.List接口架构
     * |----Collection接口：单列集合，用来存储一个一个的对象
     *         |----List接口：存储有序的、可重复的数据   -->“动态”数组，替换原有的数组
     *         |---------ArrayList: 作为List接口的主要实现类：线程不安全的，效率高：底层使用Object[] elementData存储
     *         |---------LinkedList：对于频发的插入、删除操作，使用此类效率比ArrayList高；底层使用双向链表存储
     *         |---------Vector；作为List接口的古老实现类；线程安全的，效率低，底层使用Object【】 elementData存储
     * 2. ArrayList的源码分析：
     * 2.1 jdk 7情况下
     *      ArrayList list = new ArrayList（）；//底层创建了长度是10的Object【】数组elementData
     *      list.add(123);//elementData[0] = new Integer(123)
     *      ...
     *      list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。
     *      默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
     *
     *      结论：建议开发中使用带参的构造器： ArrayList list = new ArrayList(int capacity)
     * 2.2 jdk 8中ArrayList的变化：
     *      ArrayList list = new ArrayList（）；//底层Object[] elementData初始化为{}，并没有创建长度为10的数组
     *      list.add(123);//第一次调用add()时，
     *
     */
    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        //方式一：Iterator迭代器方式
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("*****************");

        //方式二：增强for循环
        for(Object obj : list){
            System.out.println(obj);
        }
        System.out.println("******************");
        //方式三： 普通for循环
        for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AAA");
        list.add(new PersonLgy("Tom", 12));
        list.add(456);
        // int indexOf(Object obj):返回obj在集合中首次出现的位置。如果不存在，返回-1；
        int index = list.indexOf(4567);
        System.out.println(index);
        //int lastIndexOf(Object obj): 返回obj在当前集合中末位出现的位置。如果不存在，返回-1
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index): 移除指定index位置的元素，并返回元素
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        //Object set(int index, Object ele):设置指定index位置的元素为ele
        list.set(1,"CC");
        System.out.println(list);

        //List subList(int fromIndex, int toIndex): 返回从fromIndex到toIndex位置的左闭右开区间的子集合
        List subList = list.subList(2, 4);
        System.out.println(subList);
        System.out.println(list);
    }
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new PersonLgy("Tom",12));
        list.add(456);

        System.out.println(list);

        //void add(int index, Object ele):在index 位置插入ele元素
        list.add(1,"BB");
        System.out.println(list);

        //boolean addAll(int index, Collection eles):从index位置开始将eles中所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        System.out.println(list.size());
        System.out.println(list.get(0));
    }
}

