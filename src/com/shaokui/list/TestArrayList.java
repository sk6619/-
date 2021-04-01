package com.shaokui.list;

import com.shaokui.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>文档描述：</p>
 *
 * @Author TestArrayList
 * @Date 2020/5/7 0007 上午 11:48
 * @Version 1.0
 */
public class TestArrayList {


    public static void main(String[] args) {
        int A[] = {1,2,3,};
        /*List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        System.out.println(list1.get(8));
        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        list1.forEach(temp->{

        });*/
        MyArraylist<Object> list = new MyArraylist<>();
        System.out.println(list.getSize());
        System.out.println(list.elementData.length);
        MyArraylist<Integer> list2 = new MyArraylist<>();
        for(int i=0;i<23;i++) {
            list2.add(i);
            list.add(i);
        }
        //list.add(list2);
        System.out.println("数组实际长度"+list.elementData.length);
        System.out.println("集合大小"+list.getSize());
        for(int i = 0;i<list.getSize();i++) {
            System.out.println(list.get(i));
            if (list.get(i) instanceof MyArraylist) {
                for(int j = 0; j< ((MyArraylist) list.get(i)).getSize(); j++) {
                    System.out.println(((MyArraylist) list.get(i)).get(j));
                }
            }
        }
        /*System.out.println("移除元素为"+list.remove(10));
        System.out.println("数组实际长度"+list.elementData.length);
        System.out.println("集合大小"+list.getSize());
        for(int i = 0;i<list.getSize();i++) {
            System.out.println(list.get(i));
        }*/
        list.update(0, 100);
        for(int i = 0;i<list.getSize();i++) {
            System.out.println(list.get(i));
        }
    }
}
