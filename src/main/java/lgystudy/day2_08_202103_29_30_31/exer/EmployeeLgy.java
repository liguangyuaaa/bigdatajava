package lgystudy.day2_08_202103_29_30_31.exer;

import day2_08_202103_29_30_31.src.com.atguigu.exer.Employee;

public class EmployeeLgy implements Comparable<EmployeeLgy> {
    private String name;
    private int age;
    private MyDateLgy birthday;

    public EmployeeLgy() {
    }

    public EmployeeLgy(String name, int age, MyDateLgy birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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
    public int compareTo(EmployeeLgy o) {
        return this.name.compareTo(o.name);
    }
}
