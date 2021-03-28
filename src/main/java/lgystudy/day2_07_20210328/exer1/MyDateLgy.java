package lgystudy.day2_07_20210328.exer1;

import day2_07_20210328.src.com.atguigu.exer1.MyDate;

public class MyDateLgy implements Comparable{
    private int year;
    private int month;
    private int day;

    public MyDateLgy(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDateLgy() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDateLgy{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof MyDateLgy){
            MyDateLgy m = (MyDateLgy)o;

            // 比较年
            int minusYear = this.getYear() - m.getYear();
            if(minusYear != 0){
                return minusYear;
            }
            //比较月
            int minusMonth = this.getMonth() - m.getMonth();
            if(minusMonth != 0){
                return minusMonth;
            }
            //比较日
            return this.getDay() - m.getDay();
        }
        throw new RuntimeException("传入的数据类型不一致");
    }
}
