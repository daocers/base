package co.bugu.thread;

import java.util.Vector;

/**
 * Created by daocers on 2016/10/27.
 */
public class ThreadSubMain {
    public static void main(String[] args){
        Vector<Thread> threads = new Vector<>();
        for(int i = 0; i < 10; i++){
            Thread iThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程" + Thread.currentThread() + "执行完毕");
                }
            });
            threads.add(iThread);
            iThread.start();
        }
        for(Thread iThread: threads){
            try{
                iThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("主线执行");
    }
}
