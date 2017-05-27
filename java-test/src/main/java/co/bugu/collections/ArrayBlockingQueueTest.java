package co.bugu.collections;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by user on 2017/5/27.
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args){
        ArrayBlockingQueue queue = new ArrayBlockingQueue(8);
        Random random = new Random();
        for(int i = 0;i < 100; i++){
            new Thread(){
                public void run(){
                    try {
                        Thread.sleep(100 + random.nextInt(300));
                        queue.put(Thread.currentThread().getName());
                        System.out.println("queue set info, 长度：" + queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        for(int i = 0; i < 100; i++){
            new Thread(){
                public void run(){
                    try{
                        Thread.sleep(100 + random.nextInt(300));
                        System.out.println("取数据：" + queue.poll() + ", 剩余长度： " + queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
