package com.shaokui.reflect;

import org.junit.Test;

/**
 * <p>文档描述反射练习</p>
 *
 * @Author People
 * @Date 2020/5/6 0006 上午 11:20
 * @Version 1.0
 */
public class People {
    public String name;
    public String gender;
    private int age;

    @Test
    public static void eat() {
        System.out.println("我早饭吃了");
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
