package co.bugu.singleton.singleton2_1;

/**
 * Created by user on 2017/4/11.
 *
 * 解决懒汉模式在多线程下的问题
 * 但是这种方式效率低下，同步运行的，下一个线程想要获取对象，必须等待上一个线程释放锁
 */
public class MyObject {
    private static MyObject myObject;
    private MyObject(){

    }
    /**
     * 保证了安全性，但是效率降低，
     * */
    synchronized public static  MyObject getInstance(){
        try {
            if(myObject != null){

            }else{
                Thread.sleep(3000);
                myObject = new MyObject();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return myObject;
    }
}
