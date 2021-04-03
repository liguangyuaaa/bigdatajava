package day1_10_20210403.java;

public class ValueTransferTest {
	public static void main(String[] args) {
		
		String s1 = "hello";
		
		ValueTransferTest test = new ValueTransferTest();
		test.change(s1);
		
		System.out.println(s1);//hi~~
		
		
	}
	
	public void change(String s){
		s = "hi~~";
	}
}
