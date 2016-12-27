package co.bugu.threadTest;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by daocers on 2016/11/3.
 */
public class Test {
    public static void main(String[] args){
        System.out.println("--begin--");
        Date data1 = new Date();
        int taskSize = 5;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
    }
}
