package com.shaokui.reflect.exercise.entity;

import com.shaokui.reflect.exercise.annotation.Autowire;
import com.shaokui.reflect.exercise.annotation.Repository;
import com.shaokui.reflect.exercise.annotation.Value;
import lombok.Data;

/**
 * <p>文档描述：</p>
 *
 * @Author Book
 * @Date 2021/4/4 0004 下午 4:38
 * @Version 1.0
 */
@Repository
@Data
public class Book {
    @Value("1001")
    private Integer id;
    @Value("黑暗血时代")
    private String name;
}
