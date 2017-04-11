package co.bugu.singleton.singleton1;

/**
 * Created by user on 2017/4/11.
 * 多线程下不安全的单利
 */
public class MyObject {
    private static MyObject myObject;

    private MyObject(){

    }

    public static MyObject getInstance(){
        if(myObject != null){

        }else{
            myObject = new MyObject();
        }
        return myObject;
    }
}