package mainshi;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean FLAG = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**
     * 生产
     */
    public void myProd() throws InterruptedException {
        String data = null;
        boolean result;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列 " + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列 " + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 停止生产");
    }

    /**
     * 消费
     */
    public void use() throws InterruptedException {
        String data;
        while (FLAG) {
            data = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (data == null || "".equals(data)) {
                FLAG = false;
                System.out.println("退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 成功取出" + data);
        }
    }

    public void stop() {
        FLAG = false;
    }
}

public class BlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));

        new Thread(() -> {
            System.out.println("生产线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("消费线程启动");
            try {
                myResource.use();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
    }
}
