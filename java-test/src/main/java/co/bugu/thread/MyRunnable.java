package co.bugu.thread;

/**
 * Created by daocers on 2016/11/3.
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
