package co.bugu;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by user on 2017/2/28.
 */
public class BlockingQueusTest {
    private int size = 20;
    private ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(size);
    public static void main(String[] args){
        BlockingQueusTest test = new BlockingQueusTest();
        Producer producer = test.new Producer();
        Consumer consumer =  test.new Consumer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread{
        public void run(){
            while(true){
                try{
                    blockingQueue.take();//从阻塞队列中取出一个元素
                    System.out.println("队列剩余：" + blockingQueue.size() + "个元素。");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends Thread{
        public void run(){
            while(true){
                try{
                    blockingQueue.put(1);
                    System.out.println("队列剩余空间：" + (size - blockingQueue.size()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}