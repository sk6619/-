package com.shaokui.reflect.exercise.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *给类一个标识，被标记的类需要扫描注入ioc
 */
@Target(ElementType.TYPE)//用在类上
@Retention(RetentionPolicy.RUNTIME)//运行时起作用
public @interface Repository {
     String value() default "";
}
