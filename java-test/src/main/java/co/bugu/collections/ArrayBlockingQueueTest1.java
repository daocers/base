package co.bugu.collections;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by user on 2017/5/27.
 */
public class ArrayBlockingQueueTest1 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(5);
        queue.put("1");
        queue.put("2");
        queue.put("3");
        queue.put("4");
        queue.put("5");

        queue.poll();
        queue.put("6");
        System.out.println(queue.take());
        System.out.println(queue.poll());
    }
}

