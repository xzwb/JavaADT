package com.xzwb.linearArray;

import java.util.Arrays;

public class LinearArray<T> {
    // 初始化长度
    private final int initSize = 4;
    // 向量的长度
    private int size;
    // 向量已使用长度
    private int length;
    // 向量数组
    private Object[] array;
    // 空构造函数
    public LinearArray() {
        this.length = 0;
        this.array = new Object[initSize];
        this.size = initSize;
    }
    /**
     * 含参数构造
     */
    public LinearArray(int size) {
        this.length = 0;
        if (size > initSize) {
            array = new Object[size];
            this.size = size;
        } else if (size >= 0) {
            array = new Object[initSize];
        } else {
            throw new IllegalArgumentException("数组长度不能小于0");
        }
    }

    public LinearArray(T[] array) {
        this.length = array.length;
        this.array = array;
        this.size = array.length;
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public int getLength() {
        return length;
    }

    private void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "LinearArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    /**
     * 数组的拷贝,用于数组扩容时
     * @param newArray
     * @return
     */
    private Object[] arrayCopy(Object[] newArray) {
        for (int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * 数组的扩容
     */
    private void expand() {
        this.size = size<<1;
        this.array = arrayCopy(new Object[size]);
    }

    /**
     * 添加数组元素
     * @param ele
     */
    public void add(T ele) {
        if (length == size) {
            expand();
        }
        array[length] = ele;
        length++;
    }

    /**
     * 查询对象所在秩(rank)若没有返回-1,有返回最先遇到的
     * @param ele
     * @return
     */
    public int find(T ele) {
        for (int i = 0; i < length; i++) {
            if (ele.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
}
