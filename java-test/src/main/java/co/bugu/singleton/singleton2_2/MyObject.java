package co.bugu.singleton.singleton2_2;

/**
 * Created by user on 2017/4/11.
 */
public class MyObject {
    private static MyObject myObject;

    private MyObject(){

    }
    public static MyObject getInstance(){
        try{
            if(myObject != null){

            }else {
                synchronized (MyObject.class){
//                    这个时间差内仍然能造成多线程下的问题
//                    第一个获取到锁的线程在睡眠期间，后续线程也会执行到等待锁的这段，只有会重复生成
                    Thread.sleep(2000);
                    myObject = new MyObject();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return myObject;
    }
}
