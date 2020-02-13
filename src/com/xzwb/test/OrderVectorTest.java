package com.xzwb.test;

import com.xzwb.linearArray.OrderVector;
import com.xzwb.testClass.OrderVectorPojo;

public class OrderVectorTest {
    public static void main(String[] args) {
        OrderVector<Integer> array = new OrderVector<>();
        array.add(3);
        array.add(6);
        array.add(3);
        System.out.println(array);
        OrderVector<OrderVectorPojo> array1 = new OrderVector<>();
        OrderVectorPojo orderVectorPojo = new OrderVectorPojo(1);
        OrderVectorPojo orderVectorPojo1 = new OrderVectorPojo(2);
        array1.add(orderVectorPojo);
        array1.add(orderVectorPojo1);
        array1.add(new OrderVectorPojo(3));
        System.out.println(array1);
        System.out.println(array1.search(orderVectorPojo1));
    }
}
