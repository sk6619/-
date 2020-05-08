package com.shaokui.list;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>文档描述：测试linkedlist</p>
 *
 * @Author TestLinketList
 * @Date 2020/5/7 0007 下午 3:23
 * @Version 1.0
 */
public class TestLinkedList {

    public static void main(String[] args) {

        /*List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.get(1));
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("第一个线程开始");
                list.add(3);
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("第二个线程开始");
                list.add(4);
            }
        };
        t1.start();
        t2.start();
        list.forEach(temp-> System.out.println(temp));*/
        MylinkedList<Integer> list = new MylinkedList<>();
        for(int i = 0;i<100;i++) {
            list.add(i);
        }
        System.out.println(list.get(98));
        System.out.println("修改前值为："+list.update(98, 1111));
        System.out.println("修改后的值为："+list.get(98));
        /*System.out.println(list.get(99));*/

        //测试移除
        /*list.remove(0);
        System.out.println(list.size);
        System.out.println(list.get(0));*/

    }

}
