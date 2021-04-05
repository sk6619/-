package com.shaokui.reflect.exercise.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解给字段赋值
 */
@Target(ElementType.FIELD)//用在字段上
@Retention(RetentionPolicy.RUNTIME)//运行时起作用
public @interface Value {
    String value();
}
