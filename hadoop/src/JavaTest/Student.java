package JavaTest;

/**
 * Created by hadoop on 3/24/16.
 */
public class Student {
    private int age;
    public String name;
    public Student(){
        super();
    }
    public Student(int a, String n){
        super();
        this.age = a;
        this.name = n;
    }
    public void method(){
        System.out.printf("student method");
    }

    public void show(String name, int num) {
        System.out.printf("student name: " + name + " num:" + num);
    }

    public String toString(){
        return "age: " + age + "\nname: " + name;
    }
}
