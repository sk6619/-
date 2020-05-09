package com.shaokui.set;

import java.util.*;

/**
 * <p>文档描述：HashSet</p>
 *
 * @Author HashSet
 * @Date 2020/5/9 0009 上午 10:06
 * @Version 1.0
 */
public class TestHashSet {

    public static void main(String[] args) {

        Set<User> set = new HashSet<>();

        User user1 = new User(10,"张三");
        User user2 = new User(10, "张三");

        System.out.println(user1.equals(user2));

        System.out.println(set.add(user1));
        System.out.println(set.add(user2));

        set.forEach(user->System.out.println(user));

        /*set.add(1);
        set.add(2);

        System.out.println("集合大小"+set.size());

        //底层就是一个增强for循环
        set.forEach(x-> System.out.println(x));

        for(Integer i:set) {
            System.out.println(i);
        }

        System.out.println(set.contains(1));
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
    }
}
