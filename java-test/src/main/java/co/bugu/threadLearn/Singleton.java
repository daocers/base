package co.bugu.threadLearn;

import java.text.SimpleDateFormat;

/**
 * Created by daocers on 2016/11/5.
 */
public class Singleton {
    private static Singleton singleton = null;

    static {
        singleton = new Singleton();
    }
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public static void main(String[] args){
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());
        System.out.println(new Integer(1));
        System.out.println(new Integer(1));
        System.out.println(new SimpleDateFormat());
    }
}
