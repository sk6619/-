package com.shaokui.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>文档描述：测试性能</p>
 *
 * @Author TestProperty
 * @Date 2020/5/8 0008 下午 7:51
 * @Version 1.0
 */
public class TestProperty {

    /***
     * 测试结果：添加数据ArrayList消耗时间比linkedList多
     *          遍历数据ArrayList大幅度领先linkedlist
     */
    @Test
    public void test() {
        System.out.println("测试添加数据的时间===========================");
        List<Integer> arrayList = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("ArrayList添加一万条数据需要时间："+(end1-start1));
        System.out.println("..........................................");

        List<Integer> linkedList = new LinkedList<>();
        long start2 = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("linkedList添加一万条数据需要时间："+(end2-start2));

        System.out.println("测试遍历需要的时间==============================");
        long start3 = System.currentTimeMillis();
        for(int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("ArrayList:遍历需要时间："+(end3-start3));
        long start4 = System.currentTimeMillis();
        for(int i =0;i<linkedList.size();i++) {
            linkedList.get(i);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("linkedlist遍历消耗时间："+(end4-start4));
    }

    /**
     * 近乎10倍的差距
     */
    @Test
    public  void test2() {
        ArrayList<Integer> list1 = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
           for(int i = 0;i < 50000;i++){
               list1.add(i);
           }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 50000;i < 100000;i++){
                list1.add(i);
            }
        });
        t1.start();
        t2.start();
        long end1 = System.currentTimeMillis();
        System.out.println("两个线程耗时："+(end1-start1));

        ArrayList<Integer> list2 = new ArrayList<>();
        long start2 = System.currentTimeMillis();
        for(int i = 0;i < 100000;i++){
            list2.add(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("一个线程耗时："+(end2-start2));
    }

}
