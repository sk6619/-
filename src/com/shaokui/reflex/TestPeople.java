package com.shaokui.reflex;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * <p>文档描述：操作people类</p>
 *
 * @Author TestPeople
 * @Date 2020/5/6 0006 上午 11:44
 * @Version 1.0
 */
public class TestPeople {

    //Class.forName("")
    @Test
    public void test1() {
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\com\\shaokui\\reflex\\Peolple.properties"));
            String className = properties.getProperty("People");
            Class clazz = Class.forName(className);
            People p = (People) clazz.newInstance();
            Field name = clazz.getField("name");
            name.set(p, properties.getProperty("name"));
            System.out.println(p.name);
        }catch (Exception ex) {
            //抛出找不到该类的异常
        }
    }
    //类名.class
    @Test
    public void test2() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = People.class;
        People p = new People();
        Field name = clazz.getField("name");
        name.set(p, "张三");
        System.out.println(p.name);
        //获取方法
        Method method = clazz.getMethod("eat", null);
        method.invoke(p, null);
        Method[] methods = clazz.getMethods();
        for(Method method1:methods) {
            System.out.println(method1.getAnnotation(Test.class));
        }
    }
    //对象.getClass()
    @Test
    public void test3() {
        People people = new People();
        Class clazz = people.getClass();
        Method[] methods = clazz.getMethods();
        for(Method method:methods) {
           Annotation[] annotations = method.getAnnotations();
           for(Annotation annotation:annotations) {
               System.out.println(annotation);
           }
        }
    }
}
