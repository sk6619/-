package com.shaokui.set;

import java.util.Comparator;

/**
 * <p>文档描述：</p>
 *
 * @Author User
 * @Date 2020/5/9 0009 上午 11:07
 * @Version 1.0
 */
public class User implements Comparable {

    int age;
    String name;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return age+name.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return this.name == user.name && this.age == user.age;
    }


    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        User user = (User)o;
        return user.age-this.age;
    }
}
