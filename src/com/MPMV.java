package com;

import java.util.ArrayList;
import java.util.List;

/**
 * 多生产多消费
 */
public class MPMV {
    // 互斥资源 大小为1
    public static List list = new ArrayList();

    // 生产者1
    static void prod1() {
        while (true) {
            // 检查
            synchronized (list) {
            while (list.size() == 0) {
                // 加锁

                    list.add(Math.random());
                    System.out.println("生产者1 生产");
                }
            }
        }
    }
    // 生产者2
    static void prod2() {
        while (true) {
            // 检查
            synchronized (list) {
            while (list.size() == 0) {
                // 加锁

                    list.add(Math.random());
                    System.out.println("生产者2 生产");
                }
            }
        }
    }

    // 消费者1
    static void coun() {
        while (true) {
            synchronized (list) {
            while (list.size() != 0) {

                    System.out.println("消费者1消费" + list.remove(0));
                }
            }
        }
    }

    // 消费者2
    static void coun2() {
        while (true) {
            synchronized (list) {
            while (list.size() != 0) {

                    System.out.println("消费者2消费" + list.remove(0));
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            prod1();
        }).start();

        new Thread(() -> {
            prod2();
        }).start();

        new Thread(() -> {
            coun();
        }).start();

        new Thread(() -> {
            coun2();
        }).start();
    }
}
