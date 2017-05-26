package co.bugu.singleton.singleton4;

import co.bugu.singleton.SingletonSimple;

/**
 * Created by user on 2017/5/26.
 */
public class Main {

    public static void main(String[] args){
        for(int i = 0;i  < 100;i++){
            new Thread(){
                public void run(){
                    try {
                        System.out.println(SingletonSimple.getInstance());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
