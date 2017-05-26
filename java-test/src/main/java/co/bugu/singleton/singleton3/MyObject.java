package co.bugu.singleton.singleton3;

import java.util.Random;

/**
 * Created by user on 2017/4/11.
 *
 * 双重检索功能，解决了懒汉模式遇到的多线程问题
 *
 * DCL 双重检索
 *
 */
public class MyObject {
    private volatile static MyObject myObject;

    private MyObject(){

    }
    public static MyObject getInstance(){
        try {
            if(myObject != null){

            }else{
                synchronized (MyObject.class){
                    /**可能会有多个线程进入到该处，然后睡眠*/
                    Thread.sleep(new Random().nextInt(1000) + 1000);

                    /**
                     * 睡醒之后要再次判断
                     * */
                    if(myObject == null){
                        myObject = new MyObject();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return myObject;
    }
}
