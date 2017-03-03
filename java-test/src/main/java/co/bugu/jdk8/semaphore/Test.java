package co.bugu.jdk8.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by user on 2017/3/3.
 */
public class Test implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + " done" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Test test = new Test();
        for (int i = 0; i < 20; i++) {
            executorService.execute(test);
        }
        executorService.shutdown();
    }
}
