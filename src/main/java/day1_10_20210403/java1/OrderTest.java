package day1_10_20210403.java1;


import day1_10_20210403.java.Order;

public class OrderTest {
	public static void main(String[] args) {
		Order order = new Order();

		order.orderPublic = 2;
		// 出了Order类所属的包之后，私有的结构、缺省声明的结构就不可以调用了
//		order.orderDefault = 1;
		// order.orderPrivate = 3;//The field Order.orderPrivate is not visible

		order.methodPublic();
		// 出了Order类所属的包之后，私有的结构、缺省声明的结构就不可以调用了
//		order.methodDefault();
		// order.methodPrivate();//The method methodPrivate() from the type Order is not visible
	}
}
