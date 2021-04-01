package lgystudy.day2_07_20210328.java;

import java.util.Objects;

public class UserLgy {
    private String name;
    private int age;

    public UserLgy() {
    }

    public UserLgy(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "UserLgy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLgy userLgy = (UserLgy) o;
        return age == userLgy.age && Objects.equals(name, userLgy.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode():0;
        result = 31 * result + age;
        return result;
    }
    
}
