package com.xzwb.linearArray;

import java.util.Arrays;

/**
 * 无序向量
 * @param <T>
 */
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
        StringBuilder stringBuilder = new StringBuilder("LinearArray{array=[");
        for (int i = 0; i < length-1; i++) {
            stringBuilder.append(array[i]+", ");
        }
        stringBuilder.append(array[length-1] + "]}");
        return stringBuilder.toString();
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

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    /**
     * 删除一个元素
     * @param ele
     */
    public void remove(T ele) {
        int index = find(ele);
        if (index < 0) {
            return;
        } else {
            remove(index, index+1);
        }
    }

    /**
     * 删除标号为index的元素
     * @param index
     */
    public void remove(int index) {
        remove(index, index+1);
    }

    /**
     * 删除编号为begin到标号为end区间的元素不包括end但包括begin
     * @param begin
     * @param end
     */
    public void remove(int begin, int end) {
        if (isEmpty()) {
            return;
        }
        if (begin < 0) {
            begin = 0;
        }
        if (end >= length) {
            length = begin;
            return;
        }
        while (end < length) {
            array[begin] = array[end];
            begin++;
            end++;
        }
        length = length - (end - begin);
    }

    /**
     * 删除所有元素
     */
    public void removeAll() {
        array = new Object[initSize];
        length = 0;
        size = initSize;
    }

    /**
     * 判断数组是否为空,为空返回true,不为空返回false
     * @return
     */
    public boolean isEmpty() {
        if (length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 返回特定标号的元素
     * @param index
     * @return
     */
    public Object get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index >= length) {
            return array[length-1];
        } else if (index < 0) {
            return array[0];
        } else {
            return array[index];
        }
    }

    /**
     * 在index位置后添加元素
     * @param ele
     * @param index
     */
    public void add(T ele, int index) {
        if (index < 0) {
            index = 0;
        }
        if (isEmpty() || index > length) {
            add(ele);
        } else {
            if (length == size) {
                expand();
            }
            for (int i = length; i > index; i--) {
                array[i] = array[i-1];
            }
            array[index] = ele;
            length++;
        }
    }

    /**
     * 删除重复元素返回新的向量长度
     * @return
     */
    public int deduplicate() {
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i].equals(array[j])) {
                    remove(i, i+1);
                    i--;
                }
            }
        }
        return length;
    }
}
