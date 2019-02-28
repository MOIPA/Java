package com.tr.BasicJava;

import java.lang.reflect.Field;

public class Reflect {
    public static void main(String[] main) {
//        Gen<String> stringGen = new Gen<String>("aa");
//        stringGen.showTypeName();
        Gen<Person> personGen = new Gen<Person>(new Person());
        personGen.showTypeName();

        try{
            System.out.println("enter try");
            return;
        }catch (Exception e){
            System.out.println("enter catch");
        }finally {
            System.out.println("enter finally");
        }
    }
}
class Gen<T>{
    private T o;

    public Gen(Object aa) {
        o= (T) aa;
    }

    public void showTypeName(){
        System.out.println(o.getClass().getName());
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println(declaredFields[i].getName());
        }
    }

}
class Person{
    private String name;
    private int age;

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