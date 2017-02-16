package co.bugu.tes.thread;


import java.util.concurrent.*;

/**
 * Created by user on 2017/2/16.
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis() / 1000);
        MyCallable call1 = new MyCallable();
        MyCallable call2 = new MyCallable();
        MyCallable call3 = new MyCallable();
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer> future1 = es.submit(call1);
        Future<Integer> future2 = es.submit(call2);
        Future<Integer> future3 = es.submit(call3);

        Thread.sleep(2000);

        if(!future1.isDone()){
            System.out.println("1 未完成");
            future1.cancel(true);
        }
        if(!future2.isDone()){
            System.out.println("2 未完成");

            future2.cancel(true);
        }
        if(!future3.isDone()){
            System.out.println("3 未完成");

            future3.cancel(true);
        }
//        while(future1.get() + future2.get() + future3.get() != 3){
//            System.out.println("waiting...");
//        }

        System.out.println(System.currentTimeMillis() / 1000);

        System.out.println("done.");
        es.shutdownNow();
    }
}

class MyCallable implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
//        Thread.sleep(ThreadLocalRandom.current().nextInt(3) * 1000);
        Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(3) * 1000);
//        throw new Exception("over");
        return 1;
    }
}
