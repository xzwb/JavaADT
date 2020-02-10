package com.xzwb.test;

import com.xzwb.linearArray.GenericArray;

public class GenericArrayTest {
    public static void main(String[] args) {
        GenericArray array = new GenericArray();
        array.add(1);
        array.add(3);
        array.add(2);
        array.sort();
        System.out.println(array);
    }
}
