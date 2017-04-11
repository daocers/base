package co.bugu.singleton.singleton0;

/**
 * Created by user on 2017/4/11.
 * 饿汉模式
 *
 */
public class MyObject {
    private static MyObject myObject = new MyObject();

    private MyObject(){

    }

    public static MyObject getInstance(){
        return myObject;
    }
}
