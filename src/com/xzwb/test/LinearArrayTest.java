package com.xzwb.test;

import com.xzwb.linearArray.LinearArray;



public class LinearArrayTest {
    public static void main(String[] args) {
//        LinearArray<Integer> array = new LinearArray<>(10);
//        System.out.println(array.getLength());
//        System.out.println(array.getSize());
//        Integer[] integers = {1, 3, 2};
//        LinearArray<Integer> array1 = new LinearArray<>(integers);
//        System.out.println(array1);
//        System.out.println(array1.getSize());
//        System.out.println(array1.getLength());
//        LinearArray array2 = new LinearArray();
//        System.out.println(array2.getSize());
//        System.out.println(array2.getLength());
//        LinearArray array3 = new LinearArray(-1);
//        array1.add(4);
//        System.out.println(array1.getLength());
//        System.out.println(array1.getSize());
//        System.out.println(array1);
//        System.out.println(array1.find(4));
//        System.out.println(array1.find(5));
//        array1.remove(1);
//        System.out.println(array1);
        LinearArray<String> stringLinearArray = new LinearArray<>();
        stringLinearArray.add("hello");
        stringLinearArray.add("hi");
        stringLinearArray.add("aaaaa");
        stringLinearArray.add("hi");
//        System.out.println(stringLinearArray);
//        stringLinearArray.remove(1);
//        System.out.println(stringLinearArray);
//        stringLinearArray.remove("hello");
//        System.out.println(stringLinearArray);
//        System.out.println(stringLinearArray.get(1));
//        System.out.println(stringLinearArray.find("aaaaa"));
//        stringLinearArray.add("hello", 0);
//        System.out.println(stringLinearArray);
        System.out.println(stringLinearArray.deduplicate());
        System.out.println(stringLinearArray);
    }
}
