package com.sk.redis.example.decorate;

public class Student extends Person {

    public Student(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void desc() {
        System.out.println("小学生" + name);
    }

    public static void main(String[] args){
        Person person = new Student("tomy");
        person = new Shoe(person);
        person = new Cloth(person);
        person.desc();
        System.out.println(person.getName());

    }
}
