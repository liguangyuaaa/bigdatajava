package lgystudy.day2_07_20210328.exer1;

import day2_07_20210328.src.com.atguigu.exer1.Employee;

/**定义一个Employee类型。
 * 该类包含：private成员变量name,age,birthday,其中birthday为MyDate类的对象
 * 并为每一个属性定义getter, setter方法
 * 并重写toString方法输出name，age, birthday
 *
 */
public class EmployeeLgy implements Comparable{
    private String name;
    private int age;
    private MyDateLgy birthday;

    public EmployeeLgy(String name, int age, MyDateLgy birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public EmployeeLgy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyDateLgy getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDateLgy birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "EmployeeLgy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof EmployeeLgy){
            EmployeeLgy e = (EmployeeLgy)o;
            return this.name.compareTo(e.name);
        }
        throw new RuntimeException("传入的数据类型不一致");

    }
}
