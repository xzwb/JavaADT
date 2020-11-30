package cs;

import java.util.ArrayList;
import java.util.List;

/**
 *生产者消费者 (1 1)
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String lock = "lock";

        // 生产者
        new Thread(() -> {
            synchronized (lock) {
                while (true) {
                    if (list.size() != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String s = "aaa" + Math.random();
                    list.add(s);
                    System.out.println(s);
                    lock.notify();
                }
            }
        }, "s").start();

        // 消费者
        new Thread(() -> {
            synchronized (lock) {
                while (true) {
                    if (list.size() == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String s = list.remove(0);
                    System.out.println(s);
                    lock.notify();
                }
            }
        }, "p").start();
    }
}
