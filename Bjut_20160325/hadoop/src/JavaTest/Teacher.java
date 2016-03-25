package JavaTest;

/**
 * Created by hadoop on 3/24/16.
 */
public class Teacher {
    private int age;
    public String name;
    public Teacher(){

    }
    public void method(){
        System.out.printf("Teacher Method");
    }

    public void show(String name) {
        System.out.printf("teacher name: " + name);
    }

    public Teacher(int a, String n){
        this.age = a;
        this.name = n;
    }

    public String toString(){
        return "age: " + age + "name: " + name;
    }
}
