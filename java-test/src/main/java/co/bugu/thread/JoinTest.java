package co.bugu.thread;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;

/**
 * Created by user on 2017/3/9.
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        System.out.println(begin);
        MyRunnable6 runnable = new MyRunnable6();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();

        Thread thread1 = new Thread(runnable);
        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(runnable);
        thread2.start();
        thread2.join();

        Thread thread3 = new Thread(runnable);
        thread3.start();
        thread3.join();

        Thread thread4 = new Thread(runnable);
        thread4.start();
        thread4.join();

        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println(end - begin);

    }
}

class MyRunnable6 implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
