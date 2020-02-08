package com.xzwb.linearArray;

import java.util.Arrays;

/**
 *有序向量
 * @param <T>
 */
public class OrderVector<T> {
    private Object[] array;
    private int length;
    private int size;
    private int initSize = 4;

    /**
     * 无参构造
     */
    public OrderVector() {
        array = new Object[initSize];
        size = initSize;
        length = 0;
    }

    /**
     * 含参数构造
     * @param size
     */
    public OrderVector(int size) {
        if (size < initSize) {
            array = new Object[initSize];
            this.size = initSize;
        } else {
            this.size = size;
            array = new Object[size];
        }
        length = 0;
    }

    /**
     * 数组的扩容
     */
    public void expand() {
        if (length == size) {
            size <<= 1;
            array = arrayCopy(new Object[size]);
        }
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

    @Override
    public String toString() {
        return "OrderVector{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    /**
     * 插入元素
     * @param ele
     * @param <T>
     */
    public <T extends Comparable> void add(T ele) {
        expand();
        if (length == 0) {
            array[length] = ele;
            length++;
        } else {
            int i;
            for (i = 0; i < length; i++) {
                if (ele.compareTo(array[i]) < 0) {
                    add(ele, i);
                    break;
                }
            }
            if (i==length) {
                add(ele, length);
            }
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * 将元素插入特定位置
     * @param ele
     * @param index
     * @param <T>
     */
    private <T extends Comparable> void add(T ele, int index) {
        for (int i = length; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = ele;
        length++;
    }
}
