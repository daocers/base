package co.bugu.singleton.singleton0;

/**
 * Created by user on 2017/4/11.
 */
public class MyThread extends Thread {
    @Override
    public void run(){
        System.out.println(MyObject.getInstance().hashCode());
    }
}
