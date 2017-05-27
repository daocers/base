package co.bugu.collections;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by user on 2017/5/27.
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(8);
        queue.put("q");
        queue.take();
    }
}
