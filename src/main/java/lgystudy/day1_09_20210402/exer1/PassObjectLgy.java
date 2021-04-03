package lgystudy.day1_09_20210402.exer1;

import day1_09_20210402.atguigu.exer1.Circle;

/**
 * 考查参数的值传递
 *
 * 定义一个类PassObject，在类中定义一个方法printAreas().
 * 该方法的定义如下：public void printAreas(Circle c, int time)
 */
public class PassObjectLgy {
    public static void main(String[] args) {
        PassObjectLgy test = new PassObjectLgy();
        Circle c = new Circle();
        test.printAreas(c, 5);
        System.out.println("now radius is " + c.radius);

    }
    public void printAreas(Circle c, int time){
        System.out.println("Radius\t\tArea");
        int i = 1;
        for(;i <= time; i++){
            //设置圆的半径
            c.radius = i;
            double area = c.findArea();
            System.out.println(c.radius + "\t\t" + area);
        }
    }
}
