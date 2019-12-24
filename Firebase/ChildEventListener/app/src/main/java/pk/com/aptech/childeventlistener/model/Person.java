package pk.com.aptech.childeventlistener.model;

public class Person {

    private String name;
    private int age;

    //parameterized Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //Non-para.....
    public Person() {

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
}
