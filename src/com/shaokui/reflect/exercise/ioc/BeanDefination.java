package com.shaokui.reflect.exercise.ioc;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>文档描述：封装类的信息</p>
 *
 * @Author BeanDefination
 * @Date 2021/4/4 0004 下午 5:04
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class BeanDefination {

    private Class<?> beabClass;

    private String beanName;
}
