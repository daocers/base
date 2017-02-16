package co.bugu.tes.thread;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by user on 2017/2/16.
 */
public class TestSingleThreadExecutor {
    public static void main(String[] arga){
        List<Integer> box = new ArrayList<>();
        ExecutorService pool = Executors.newSingleThreadExecutor();

        pool.execute(new MyThread(box));
        pool.execute(new MyThread(box));
        pool.execute(new MyThread(box));
        pool.execute(new MyThread(box));
        pool.execute(new MyThread(box));

        System.out.println(JSON.toJSONString(box));
        pool.shutdown();

    }
}

class MyThread implements Runnable{

    List<Integer> box;
    public MyThread(List<Integer> box){
        this.box = box;
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + "执行中。。。");
        box.add(box.size());
        System.out.println("my box have " + box.size());
    }
}
