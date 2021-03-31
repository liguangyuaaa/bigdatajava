package tzgstudy.day02.java1;


import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * TreeSet简介
 * TreeSet 是一个有序的集合，它的作用是提供有序的Set集合。它继承于AbstractSet抽象类，实现了NavigableSet<E>, Cloneable, java.io.Serializable接口。
 * TreeSet 继承于AbstractSet，所以它是一个Set集合，具有Set的属性和方法。
 * TreeSet 实现了NavigableSet接口，意味着它支持一系列的导航方法。比如查找与指定目标最匹配项。
 * TreeSet 实现了Cloneable接口，意味着它能被克隆。
 * TreeSet 实现了java.io.Serializable接口，意味着它支持序列化。
 * TreeSet是基于TreeMap实现的。TreeSet中的元素支持2种排序方式：自然排序 或者 根据创建TreeSet 时提供的 Comparator 进行排序。这取决于使用的构造方法。
 * TreeSet为基本操作（add、remove 和 contains）提供受保证的 log(n) 时间开销。
 * 另外，TreeSet是非同步的。 它的iterator 方法返回的迭代器是fail-fast的。

 */
public class TreeSetTest
{
     /*
    1.向TreeSet中添加的数据，要求是相同类的对象。
    2.两种排序方式：自然排序（实现Comparable接口） 和 定制排序（Comparator）

    3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0.不再是equals().
    4.定制排序中，比较两个对象是否相同的标准为：compare()返回0.不再是equals().
     */

    @Test
    public void test1(){
        TreeSet set = new TreeSet();
        //举例二：
        set.add(new User("Tom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));

        //有序集合
        Iterator iterator = set.iterator();

        while (iterator.hasNext()){
            System.out.println("iterator = " + iterator.next());
        }
    }

    @Test
    public void test2() {
        Comparator com = new Comparator() {

            //按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return u2.getName().compareTo(u1.getName());
                } else {
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };

        TreeSet set = new TreeSet(com);
        set.add(new User("tom",12));
        set.add(new User("Aaom",12));
        set.add(new User("Abom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Mary",33));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));

        //有序set
        for (Object o : set) {
            System.out.println("o = " + o);
        }


    }

    @Test
    public void test4(){
        User tom = new User("Tom", 12);
        User eve = new User("Tom", 13);
        int i = tom.compareTo(eve);
        System.out.println("i = " + i);
    }
}
