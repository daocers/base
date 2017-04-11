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
                Thread.sleep(new Random().nextInt(1000) + 1000);
                synchronized (MyObject.class){
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
