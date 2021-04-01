package com.shaokui.list;

import java.util.ArrayList;

/**
 * <p>文档描述：</p>
 *
 * @Author TestMyArrayList1
 * @Date 2021/4/1 0001 上午 10:40
 * @Version 1.0
 */
public class TestMyArrayList1 {

    public static void main(String[] args) {

        MyArraylist<Integer> list = new MyArraylist<>();
        //不添加任何数据
        System.out.println("集合大小"+list.getSize()+";数组长度"+list.elementData.length);
        //添加一个数据
        list.add(1);
        System.out.println("集合大小"+list.getSize()+";数组长度"+list.elementData.length);
        //添加第二个数据
        list.add(2);
        System.out.println("集合大小"+list.getSize()+";数组长度"+list.elementData.length);
        //第一次扩容后数组长度10+10右移一位
        for (int i = 3;i <=11;i++) {
            list.add(i);
        }
        System.out.println("集合大小"+list.getSize()+";数组长度"+list.elementData.length);
        //第二次扩容
        for (int j = 12;j <= 16; j++){
            list.add(j);
        }
        System.out.println("集合大小"+list.getSize()+";数组长度"+list.elementData.length);

        //删除元素 集合大小和数组长度变换
       int x =  list.remove(10);
        System.out.println(x+"集合大小"+list.getSize()+";数组长度"+list.elementData.length);

        int B[] = {0,1,2,3,4,5,6};
        System.arraycopy(B, 2, B, 3, 1);
        for(int y = 0;y < B.length;y++) {
            System.out.println(B[y]);
        }
    }


}
