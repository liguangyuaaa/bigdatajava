package lgystudy.day2_06_20210327.java1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTestLgy {
    /**
     * 1.向TreeSet中添加的数据，要求是相同类的对象
     * 2.两种排序方式： 自然排序（实现Comparable接口） 和 定制排序（Comparator)
     * 3.自然排序中，比较两个对象是否相同的标准为：compareTo（）返回0，不再是equals().
     * 4.定制排序中，比较两个队形是否相同的标准为：compare()返回0，不再是equals（）
     *
     */
    @Test
    public void test1(){
        TreeSet set = new TreeSet();

        //java.lang.RuntimeException: 输入的类型不匹配
//        set.add(123);
//        set.add(456);
//        set.add(new UserLgy("Tom",12));

        //举例二： java.lang.RuntimeException: 输入的类型不匹配
//        set.add(new UserLgy("Tom",12));
//        set.add(new User("Jerry",32));
//        set.add(new User("Jim",2));

        set.add(123);
        set.add(456);
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof UserLgy && o2 instanceof UserLgy){
                    UserLgy u1 = (UserLgy) o1;
                    UserLgy u2 = (UserLgy) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }else{
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };

        TreeSet set = new TreeSet(com);
        set.add(new UserLgy("Tom",12));
        set.add(new UserLgy("Jerry",32));
        set.add(new UserLgy("Jim",2));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
