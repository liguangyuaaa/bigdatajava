package lgystudy.day2_07_20210328.exer;

public class PersonLgy {
    int id;
    String name;
    public PersonLgy(int id, String name){
        this.id = id;
        this.name = name;

    }
    public PersonLgy(){

    }
    @Override
    public String toString(){
        return "PersonLgy{" + "id=" + id + ",name='" + name + '\'' + '}';
    }
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PersonLgy personLgy = (PersonLgy) o;

        if(id != personLgy.id) return false;
        return name != null ? name.equals(personLgy.name) : personLgy.name ==null;

    }

    public int hashCode(){
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
