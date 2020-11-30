package cs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印A B C
 */
class MyService {
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    String flag = "A";

    /**
     * 打印A
     */
    void printA() {
        try {
            lock.lock();
            while (!flag.equals("A")) {
                conditionA.await();
            }
            System.out.println("A");
            flag = "B";
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印B
     */
    void printB() {
        try {
            lock.lock();
            while (!flag.equals("B")) {
                conditionB.await();
            }
            System.out.println("B");
            flag = "C";
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印C
     */
    void printC() {
        try {
            lock.lock();
            while (!flag.equals("C")) {
                conditionC.await();
            }
            System.out.println("C");
            flag = "A";
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


public class Test3 {
    public static void main(String[] args) {
        MyService service = new MyService();

        new Thread(() -> {
            while (true) {
                service.printA();
            }
        }, "A").start();

        new Thread(() -> {
            while (true) {
                service.printB();
            }
        }, "B").start();

        new Thread(() -> {
            while (true) {
                service.printC();
            }
        }, "C").start();
    }
}
