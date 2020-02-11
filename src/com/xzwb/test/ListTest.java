package com.xzwb.test;

import com.xzwb.list.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> list = new List<>();
        list.addFirst("123");
        list.addFirst("456");
        list.addLast("789");
        list.add("147", 0);
//        list.remove("123");
//        System.out.println(list);
//        System.out.println(list.get(0));
//        System.out.println(list.getSize());
//        System.out.println(list.selectMax());
//        List<Integer> list = new List<>();
//        list.addLast(1);
//        list.addLast(2);
//        list.addLast(3);
//        System.out.println(list.selectMax());
//        System.out.println(list);
    }
}
