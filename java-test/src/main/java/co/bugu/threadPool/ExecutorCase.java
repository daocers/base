package co.bugu.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by user on 2017/2/28.
 */
public class ExecutorCase {
    private static Executor executor = Executors.newFixedThreadPool(10);
    public static void main(String[]  args){
        for(int i = 0; i < 30; i++){
            executor.execute(new Task());
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
