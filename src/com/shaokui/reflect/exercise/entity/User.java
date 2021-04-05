package com.shaokui.reflect.exercise.entity;

import com.shaokui.reflect.exercise.annotation.Autowire;
import com.shaokui.reflect.exercise.annotation.Repository;
import com.shaokui.reflect.exercise.annotation.Value;
import lombok.Data;

/**
 * <p>文档描述：需要注入ioc的实体</p>
 *
 * @Author User
 * @Date 2021/4/4 0004 下午 4:19
 * @Version 1.0
 */
@Data
@Repository
public class User {

    @Value("10")
    private Integer age;

    @Value("小狗")
    private String name;
//    @Autowire
//    private Book book;
//    @Autowire
//    private Student student;

}
