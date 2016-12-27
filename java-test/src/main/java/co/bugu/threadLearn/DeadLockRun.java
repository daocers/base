package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/26.
 */
public class DeadLockRun {
    public static void main(String[] args) throws InterruptedException {
        DealThread t1 = new DealThread();
        t1.setFlag("a");
        Thread thread1 = new Thread(t1);
        thread1.start();
        Thread.sleep(1000);

        t1.setFlag("b");
        Thread thread2 = new Thread(t1);
        thread2.start();
    }
}
