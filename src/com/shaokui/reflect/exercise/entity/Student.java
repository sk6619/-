package com.shaokui.reflect.exercise.entity;

import com.shaokui.reflect.exercise.annotation.Autowire;
import com.shaokui.reflect.exercise.annotation.Repository;
import com.shaokui.reflect.exercise.annotation.Value;
import lombok.Data;

/**
 * <p>文档描述：</p>
 *
 * @Author Student
 * @Date 2021/4/5 0005 下午 4:40
 * @Version 1.0
 */
@Data
@Repository
public class Student {
    @Value("50")
    private Integer id;
    @Autowire
    private User user;
    @Autowire
    private Book book;
}
