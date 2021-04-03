package lgystudy.day1_08_20210401.java;

/**
 *一、设计类，其实就是设计类的成员
 *
 * 属性 = 成员变量 = field = 域、字段
 * 方法 = 成员方法 = 函数 = method
 *
 * 创建类的对象 = 类的实例化 = 实例化类
 *
 * 二、类和对象的使用（面向对象思想落地的实现）；
 * 1.创建类，设计类的成员
 * 2.创建类的对象
 * 3.通过“对象。属性”或“对象。方法“调用对象的结构
 *
 * 三、如果创建了一个类的对象，则每个对象都独立的拥有一套类的属性。（非static的）
 *  意味着：如果我们修改一个对象的属性a，则不影响另外一个对象属性a的值
 *
 * 四、对象的内存解析
 */
public class PersonTestLgy {
    public static void main(String[] args) {
        PersonTestLgy p1 = new PersonTestLgy();
    }
}
