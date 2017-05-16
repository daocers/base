package co.bugu.thread;

/**
 * Created by user on 2017/5/16.
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable0());
        thread.start();
//        thread.interrupt();
//        System.out.println(thread.isInterrupted());
////        thread.join();
        Thread.sleep(100);
//        thread.interrupt();
//        System.out.println(thread.isInterrupted());
        Thread.interrupted();
        System.out.println(thread.isInterrupted());
//        System.out.println(Thread.currentThread().isInterrupted());
    }
}

class Runnable0 implements Runnable{

    @Override
    public void run() {
        System.out.println("thread done");
    }
}
