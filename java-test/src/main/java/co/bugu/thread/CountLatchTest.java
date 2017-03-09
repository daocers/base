package co.bugu.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by daocers on 2017/3/9.
 */
public class CountLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        long begin = System.currentTimeMillis();
        System.out.println(begin);
        MyRunnable7 runnable = new MyRunnable7(latch);
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        latch.await();

        long end = System.currentTimeMillis();

        System.out.println(end - begin);

    }
}

class MyRunnable7 implements Runnable{
    private CountDownLatch latch;
    public MyRunnable7(){

    }
    public MyRunnable7(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
