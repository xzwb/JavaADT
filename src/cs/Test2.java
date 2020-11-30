package cs;

import java.util.ArrayList;
import java.util.List;

/**
 * 多生产多消费
 */
public class Test2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String lock = "lock";

        // 生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {

                    while (true) {
                        synchronized (lock) {
                        // 要用while防止虚假唤醒
                        while (list.size() != 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        String s = "aaa" + Math.random();
                        list.add(s);
                        System.out.println(s + "\t  " + Thread.currentThread().getName());
                        // 多生产和多消费要用notifyAll()
                        lock.notifyAll();
                    }
                }
            }, "s"+i).start();
        }

        // 消费者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {

                    while (true) {
                        synchronized (lock) {
                        while (list.size() == 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        String s = list.remove(0);
                        System.out.println(s + "\t  " + Thread.currentThread().getName());
                        lock.notifyAll();
                    }
                }
            }, "p"+i).start();
        }
    }
}
