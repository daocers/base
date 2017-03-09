package co.bugu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by daocers on 2017/3/9.
 */
public class ExchangerTest {
    public static void main(String[] args){
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        new Thread(new MyRunnable11(exchanger)).start();
        new Thread(new MyRunnable12(exchanger)).start();

    }

}

class MyRunnable11 implements Runnable{
    private List<Integer> list = new ArrayList<>();
    private Exchanger<List<Integer>> exchanger;
    public MyRunnable11(Exchanger<List<Integer>> exchanger){
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            System.out.println("result in MyRunnable11:");
            list.add(11);
            list.add(12);
            list.add(13);
            list = exchanger.exchange(list);
            for(Integer num: list){
                System.out.println("11: " + num);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyRunnable12 implements Runnable{
    private List<Integer> list = new ArrayList<>();
    private Exchanger<List<Integer>> exchanger;
    public MyRunnable12(Exchanger<List<Integer>> exchanger){
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            System.out.println("result in MyRunnable12:");
            list.add(21);
            list.add(22);
            list.add(23);
            list = exchanger.exchange(list);
            for(Integer num: list){
                System.out.println("12: " + num);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
