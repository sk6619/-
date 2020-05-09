package com.shaokui.set;

import org.openjdk.jol.info.ClassLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>文档描述：</p>
 *
 * @Author TestTreeSet
 * @Date 2020/5/9 0009 上午 11:38
 * @Version 1.0
 */
public class TestTreeSet {

    public static void main(String[] args) throws ParseException {

        //System.out.println(ClassLayout.parseInstance(User.class).toPrintable());
        /*Set<User> set = new TreeSet<>();

        User user1 = new User(9, "李四");
        User user2 = new User(10, "李四");
        User user3 = new User(11, "李四");
        User user4 = new User(11, "李四");
        System.out.println(set.add(user1));
        System.out.println(set.add(user3));
        System.out.println(set.add(user2));
        System.out.println(set.add(user4));

        set.forEach(user->System.out.println(user));*/
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User) {
                    User user1 = (User) o1;
                    User user2 = (User) o2;
                    return user1.age-user2.age;
                }
                return 0;
            }
        };
        Set set = new TreeSet(comparator);
        User user1 = new User(9, "李四");
        User user2 = new User(10, "李四");
        User user3 = new User(11, "李四");
        User user4 = new User(11, "李四");
        System.out.println(set.add(user1));
        System.out.println(set.add(user3));
        System.out.println(set.add(user2));
        System.out.println(set.add(user4));
        set.forEach(user->System.out.println(user));
        /*Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(date));

        String ds = format.format(date);

        System.out.println(format.parse(ds));*/

    }

}
