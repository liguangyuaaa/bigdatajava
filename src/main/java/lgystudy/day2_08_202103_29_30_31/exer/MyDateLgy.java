package lgystudy.day2_08_202103_29_30_31.exer;

public class MyDateLgy implements Comparable<MyDateLgy>{
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
    public int compareTo(MyDateLgy o) {
        //比较年
        int minusYear = this.getYear() - o.getYear();
        if(minusYear != 0){
            return minusYear;
        }
        //比较月
        int minusMonth = this.getMonth() - o.getMonth();
        if(minusMonth != 0){

        }
        return this.getDay() - o.getDay();
    }
}
