package com.shaokui.reflect;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>文档描述：广度搜索,循环代替递归</p>
 *
 * @Author BFS
 * @Date 2021/4/4 0004 上午 10:17
 * @Version 1.0
 */
public class BFS {

    public static void main(String[] args) {

        /*File files = new File("C:\\Users\\Administrator\\Desktop\\bootstrap");
        ArrayList<File> temp = new ArrayList<File>();
        temp.add(files);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).isDirectory()) {
                File[] list = temp.get(i).listFiles();
                for (File file2 : list) {
                    temp.add(file2);
                }
            }
        }
        temp.forEach(System.out::println);*/

        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        for (int i = 0; i < list.size(); i++) {
            list.add(i);
            if(i>10){
                break;
            }
        }
        list.forEach(System.out::println);
    }
}
