package com.xzwb.linearArray;

import java.util.ArrayList;
import java.util.Arrays;

public class GenericArray<T extends Comparable> {
    private int size;
    private T[] array;
    private int length;
    private final int initSize = 4;

    /**
     * 有参构造
     * @param size
     */
    public GenericArray(int size) {
        if (size < initSize) {
            array = (T[])(new Comparable[initSize]);
        } else {
            array = (T[])(new Comparable[size]);
        }
        length = 0;
        this.size = size;
    }

    /**
     * 无参构造
     */
    public GenericArray() {
        array = (T[])(new Comparable[initSize]);
        size = initSize;
        length = 0;
    }

    /**
     * 数组的拷贝
     * @param ts
     * @return
     */
    private T[] arrayCopy(T[] ts) {
        for (int i = 0; i < length; i++) {
            ts[i] = array[i];
        }
        return ts;
    }

    /**
     * 数组的扩容
     */
    private void expand() {
        if (length == size) {
            size <<= 1;
            array = arrayCopy((T[])(new Object[size]));
        }
    }

    /**
     * 添加元素
     * @param ele
     */
    public void add(T ele) {
        expand();
        array[length] = ele;
        length++;
    }

    @Override
    public String toString() {
        return "GenericArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    public <T extends Comparable> void sort() {
        sort(array);
    }

    private <T extends Comparable<T>> void sort(T[] array) {
        T temp;
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-i-1; j++) {
                if (array[j].compareTo(array[j+1]) > 0) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
