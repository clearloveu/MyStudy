package com.zs;

import leetcode.Test208;

/**
 * @author zg
 * @create 2020-01-06 12:03
 */
public class TestTest208 {


    public static void main(String[] args) {
        Test208 t = new Test208();
        t.insert("apple");
        System.out.println(t.search("apple")); //返回true
        System.out.println(t.search("app"));     // 返回 false
        System.out.println(t.startsWith("app")); // 返回 true
        t.insert("app");
        System.out.println(t.search("app"));     // 返回 true

    }
}
