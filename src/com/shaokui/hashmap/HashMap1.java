package com.shaokui.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>文档描述：</p>
 *
 * @Author HashMap1
 * @Date 2020/5/4 0004 下午 8:41
 * @Version 1.0
 */
public class HashMap1 {

    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();

        map.put("name", "张三");
        map.put("id", "1");
        map.get("id");
        map.get(2);
    }
}
