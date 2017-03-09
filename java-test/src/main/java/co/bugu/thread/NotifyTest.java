package co.bugu.thread;

/**
 * Created by user on 2017/3/9.
 */
public class NotifyTest {
    public static void main(String[] args){

    }


}

class MyRunnable5 implements Runnable{
    public void testWait() throws InterruptedException {
        synchronized (this){
            this.wait();
        }
    }

    public void testNotify(){
        synchronized (this){
            this.notify();
        }
    }
    public void testNotifyAll(){
        synchronized (this){
            this.notifyAll();
        }
    }
    @Override
    public void run() {
        try {
            testWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
