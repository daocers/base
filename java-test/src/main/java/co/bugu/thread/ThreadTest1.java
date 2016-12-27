package co.bugu.thread;

/**
 * Created by daocers on 2016/11/3.
 */
public class ThreadTest1 {

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}

