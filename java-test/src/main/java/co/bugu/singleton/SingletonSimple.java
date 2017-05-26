package co.bugu.singleton;

/**
 * Created by user on 2017/5/26.
 */
public class SingletonSimple {
    private static SingletonSimple instance;

    private SingletonSimple(){

    }



    public static SingletonSimple getInstance() throws InterruptedException {
        if(null == instance){
            Thread.sleep(10);
            synchronized (instance){
                instance = new SingletonSimple();
            }
        }
        return instance;
    }

}
