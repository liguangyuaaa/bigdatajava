package lgystudy.day2_08_202103_29_30_31.exer;

import day2_08_202103_29_30_31.src.com.atguigu.exer.Employee;
import day2_08_202103_29_30_31.src.com.atguigu.exer.MyDate;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 创建该类的5个对象，并把这些对象放入TreeSet集合中（下一章：TreeSet 需使用泛型来定义）
 * 分别按一下两种方式对集合中的元素进行排序，并遍历输出
 *
 * 1）使EmployeeLgy
 */
public class EmployeeTestLgy {
    //问题二：按生日日期的先后排序
    @Test
    public void test2(){
        TreeSet<EmployeeLgy> set = new TreeSet<>(new Comparator<EmployeeLgy>() {
            @Override
            public int compare(EmployeeLgy o1, EmployeeLgy o2) {
                MyDateLgy b1 = o1.getBirthday();
                MyDateLgy b2 = o2.getBirthday();

                return b1.compareTo(b2);
            }
        });


        EmployeeLgy e1 = new EmployeeLgy("liudehua",55,new MyDateLgy(1965,5,4));
        EmployeeLgy e2 = new EmployeeLgy("zhangxueyou",43,new MyDateLgy(1987,5,4));
        EmployeeLgy e3 = new EmployeeLgy("guofucheng",44,new MyDateLgy(1987,5,9));
        EmployeeLgy e4 = new EmployeeLgy("liming",51,new MyDateLgy(1954,8,12));
        EmployeeLgy e5 = new EmployeeLgy("liangzhaowei",21,new MyDateLgy(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator<EmployeeLgy> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
