package com.shaokui.reflect.exercise.tool;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>文档描述：工具类，扫描包获取包下的类</p>
 *
 * @Author MyTool
 * @Date 2021/4/4 0004 下午 5:43
 * @Version 1.0
 */
public class MyTool {

    //项目编译后跟路径
    public static String path = MyTool.class.getClassLoader().getResource("").getPath();

    //扫描包，找到包下所有的类
    public static List<String> getClasses(String pakageName) {
        //去除根路径第一个斜杠
        String path1 = path.substring(1);
        String paka = pakageName.replace(".", "/");
        //创建临时数组存放包下面所有文件
        ArrayList<File> temp = new ArrayList<>();
        //创建数组找到包下面所有的类
        ArrayList<String> list = new ArrayList<>();
        File file = new File(path+paka);
        temp.add(file);
        for (int i = 0; i < temp.size(); i++) {
            File file1 = temp.get(i);
            //若是java文件则加入list集合
            if(file1 != null && file1.getPath().contains(".class")) {
                //替换斜杠为点 去除".class"
                String classPath = file1.getPath().replace(path1.replace("/", "\\"), "");
                String className = classPath.replace(".class", "");
                list.add(className.replace("\\", "."));
                //如果还是包则继续遍历,将所有问价继续加入集合重新循环
            }else if (file1 != null && file1.isDirectory()) {
                File[] files = file1.listFiles();
                if(files != null) {
                    temp.addAll(Arrays.asList(files));
                }
            }
        }
        return list;
    }

}
