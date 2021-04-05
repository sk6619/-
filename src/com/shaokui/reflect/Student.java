package com.shaokui.reflect;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.xml.bind.annotation.XmlType;

/**
 * <p>文档描述：java反射</p>
 *
 * @Author Student
 * @Date 2021/4/3 0003 下午 12:15
 * @Version 1.0
 */
@Good(name = "sk")
@Deprecated
public class Student implements Person{

    @Good(name = "ul")
    private Integer age;
    protected String name;
    public String gender;

    public Student() {}

    public Student(int age,String name,String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public void run() {
        System.out.println("学生会学习");
    }

    @Good(name = "0")
    void eat(String food) {
        System.out.println(food);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
