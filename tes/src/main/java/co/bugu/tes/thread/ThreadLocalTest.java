package co.bugu.tes.thread;

import co.bugu.framework.core.mybatis.ThreadLocalUtil;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 2017/2/16.
 */
public class ThreadLocalTest {
    static ThreadLocal<Integer> t1 = new ThreadLocal<>();


    public static void main(String[] args){
//        t1.set(10);
//        Something s= new Something();
//        System.out.println(s.getNum());
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new MyRunnable(1));
        es.execute(new MyRunnable(2));
        es.execute(new MyRunnable(3));
        es.execute(new MyRunnable(4));
        es.execute(new MyRunnable(5));
        es.shutdown();
    }
}


class MyRunnable implements Runnable{
    private Integer num;
    public MyRunnable(Integer num){
        this.num = num;
        ThreadLocalTest.t1.set(num);
    }

    @Override
    public void run() {
        ThreadLocal<Integer> threadLocal = ThreadLocalTest.t1;
        System.out.println(Thread.currentThread().getName() + "  " + threadLocal.get());
    }
}
class Something{
    public Integer getNum(){
        ThreadLocal<Integer> t2 = new ThreadLocal<>();
        return t2.get();
    }
}

