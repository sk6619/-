package com.shaokui.reflect;

/**
 * <p>文档描述：</p>
 *
 * @Author Person
 * @Date 2021/4/3 0003 下午 12:59
 * @Version 1.0
 */
public interface Person {

    public default void run(){
        System.out.println("人都会跑");
    };
}
