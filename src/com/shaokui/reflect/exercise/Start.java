package com.shaokui.reflect.exercise;

import com.shaokui.reflect.exercise.entity.Book;
import com.shaokui.reflect.exercise.entity.User;
import com.shaokui.reflect.exercise.ioc.MyAnnotationAppConfigContext;

/**
 * <p>文档描述：反射练习启动类,手写ioc</p>
 *
 * @Author Start
 * @Date 2021/4/4 0004 下午 4:06
 * @Version 1.0
 */
public class Start {


    public static void main(String[] args) {


            String packageName = "com.shaokui.reflect.exercise.entity";
            MyAnnotationAppConfigContext context = new MyAnnotationAppConfigContext(packageName);
            context.getAllBeans().forEach(System.out::println);


    }


}
