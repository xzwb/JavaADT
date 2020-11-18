package mainshi;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 一池5个线程
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 一池一线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 一池多线程
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
