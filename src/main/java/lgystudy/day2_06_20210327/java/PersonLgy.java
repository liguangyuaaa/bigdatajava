package lgystudy.day2_06_20210327.java;

public class PersonLgy {
    private String name;
    private int age;
    public PersonLgy() {
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

    public PersonLgy(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
