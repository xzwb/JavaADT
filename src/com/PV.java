package com;

import java.util.ArrayList;
import java.util.List;

/**
 * 但生产但消费
 * 生产者消费者
 */
public class PV {
    // 缓冲区大小为5
    public static volatile List list = new ArrayList<>();

    // 生产者
    public static void prod() {
        while (true) {
            if (list.size() < 5) {
                synchronized (list) {
                    System.out.println("生产者生产" + list.add(Math.random()));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 消费者
    public static void consumer() {
        while (true) {
            if (list.size() > 0) {
                synchronized (list) {
                    System.out.println("消费者消费" + list.remove(0));
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            consumer();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            prod();
        }).start();


    }
}
