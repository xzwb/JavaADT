package cs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列实现生产者消费者
 */
class MyResources {
    BlockingQueue<String> blockingQueue = null;

    public MyResources(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    void myProd() throws InterruptedException {
        String data = "";
        while (true) {
            data = "aaa" + Math.random();
            blockingQueue.put(data);
            System.out.println(Thread.currentThread().getName() + "\t生产了" + data);
//            Thread.sleep(500);
        }
    }

    void myUse() throws InterruptedException {
        String result;
        while (true) {
            result = blockingQueue.take();
            System.out.println(Thread.currentThread().getName() + "\t消费了" + result);
        }
    }
}

public class BlockQueueCs {
    public static void main(String[] args) {
        MyResources resources = new MyResources(new ArrayBlockingQueue<>(3));

        new Thread(() -> {
            try {
                resources.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                resources.myUse();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
