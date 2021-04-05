package com.shaokui.reflect;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>文档描述：操作反射</p>
 *
 * @Author TestStudent
 * @Date 2021/4/3 0003 下午 1:02
 * @Version 1.0
 */
public class TestStudent {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //会抛出找不到类的异常
        /*Class stuClass = Class.forName("com.shaokui.reflect.Student");
        Class stuClass1 = Student.class;

        Student student = new Student();
        Class stuClass2 = student.getClass();



        //获取所有public字段
        Field[] fields = stuClass2.getFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName()+"类型"+fields[i].getType());
        }
        //------------------------------
        //获取所有声明的字段
        Field[] fields1 = stuClass2.getDeclaredFields();
        for (int i = 0; i < fields1.length; i++) {
            System.out.println(fields1[i].getName()+"类型"+fields1[i].getType());
        }

        System.out.println("=====================");

        //创建实例,抛出实例化异常
        Student student1 = (Student) stuClass.newInstance();
        Constructor constructor = stuClass.getConstructor(null);
        Student student2 = (Student) constructor.newInstance(null);*/

        /*//创建实例
        Class stuClass = Student.class;
        Student student1 = (Student) stuClass.newInstance();*/
        /*//通过字段给对象赋值
        Field age = stuClass.getDeclaredField("age");
        age.setAccessible(true);
        age.set(student1, 1);
        System.out.println(age.get(student1));

        Constructor constructor1 = stuClass.getConstructor( null);
        Student student2 = (Student) constructor1.newInstance();
        age.set(student2, 2);

        System.out.println(student2.getAge());
        Constructor constructor2  = stuClass.getConstructor(int.class,String.class,String.class);
        Student student3 = (Student) constructor2.newInstance(10,"王湾","女");
        System.out.println(age.get(student3));*/


        //反射获取方法

       /* Class stuClass = Student.class;
        //参数 方法名，方法参数，只能获取public修饰
        Method method = stuClass.getMethod("run", null);
        System.out.println(method.getName());
        //  非public修饰，加declared
        Method method1 = stuClass.getDeclaredMethod("eat", String.class);
        System.out.println(method1.getName());
        //获取所有方法
        Method[] methods = stuClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getName());
        }
        //调用方法
        method.invoke(new Student());*/

        //获取注解
        Class stuClass = Student.class;
        //
        Annotation annotation = stuClass.getAnnotation(Good.class);
        System.out.println(annotation);
        Annotation[] annotations = stuClass.getDeclaredAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }

        /*Field anno = stuClass.getDeclaredField("age");
        Annotation annotation1 = anno.getDeclaredAnnotation(Good.class);
        System.out.println(annotation1);

        Method[] anno0 = stuClass.getDeclaredMethods();
        Annotation[] annotation2 = anno0[0].getDeclaredAnnotations();
        for (int i = 0; i < annotation2.length; i++) {
            System.out.println(annotation2[i]);
        }*/

    }
}
