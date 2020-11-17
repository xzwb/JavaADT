package mainshi;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("i " + finalI);
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}
