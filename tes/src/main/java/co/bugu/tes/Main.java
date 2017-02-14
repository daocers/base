package co.bugu.tes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by daocers on 2017/2/14.
 */
public class Main {
    public static  void main(String[] args){
        List<Integer> flag = new ArrayList<>();
        Runnable runnable = new ThreadImpl(flag);
        System.out.println(System.currentTimeMillis()/1000);
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

        boolean f = true;
        while(f){
            if(flag.size() == 7){
                f = false;
            }
        }
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println("done...");
    }
}

class ThreadImpl implements Runnable{
    private List<Integer> list;
    public ThreadImpl(List<Integer> list){
        this.list = list;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (list){
            list.add(0);
        }

    }
}
