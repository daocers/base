package co.bugu.singleton.singleton2;

/**
 * Created by user on 2017/4/11.
 * 验证延迟加载，懒汉模式在多线程出问题
 */
public class MyObject {
    private static MyObject myObject;
    private MyObject(){

    }

    public static MyObject getInstance(){
        try{
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
