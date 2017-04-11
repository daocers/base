package co.bugu.singleton.singleton4;

/**
 * Created by user on 2017/4/11.
 */
public class MyObject {
    private static class MyObjecthHandler{
        private static MyObject myObject = new MyObject();
    }

    private MyObject(){

    }

    public static MyObject getInstance(){
        return MyObjecthHandler.myObject;
    }
}
