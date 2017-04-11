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
