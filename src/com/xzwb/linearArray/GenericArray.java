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

    /**
     * 调用排序方法
     * @param <T>
     */
    public <T extends Comparable> void sort() {
        mergeSort(array, length);
    }

    /**
     * 归并排序
     * @param array
     * @param length
     * @param <T>
     */
    private <T extends Comparable> void mergeSort(T[] array, int length) {
        if (length > 1) {
            T[] leftHalf = (T[]) new Comparable[length / 2];
            System.arraycopy(array, 0, leftHalf, 0, length/2);
            mergeSort(leftHalf, leftHalf.length);
            int rightHalfLength = length - length / 2;
            T[] rightHalf = (T[]) new Comparable[rightHalfLength];
            System.arraycopy(array, length/2, rightHalf, 0, rightHalfLength);
            mergeSort(rightHalf, rightHalfLength);

            T[] end = merge(leftHalf, rightHalf);
            System.arraycopy(end, 0, array, 0, end.length);
        }
    }

    private <T extends Comparable> T[] merge(T[] left, T[] right) {
        T[] result = (T[]) new Comparable[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (j < left.length && k < right.length) {
            if (left[j].compareTo(right[k]) <= 0) {
                result[i++] = left[j++];
            } else {
                result[i++] = right[k++];
            }
        }

        while (j < left.length) {
            result[i++] = left[j++];
        }

        while (k < right.length) {
            result[i++] = right[k++];
        }
        return result;
    }

    /**
     * 冒泡排序
     * @param array
     * @param <T>
     */
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
