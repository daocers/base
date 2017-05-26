package co.bugu.singleton.singleton2_2;

/**
 * Created by user on 2017/5/26.
 */
public class Run {
    public static void main(String[] args){
        for(int i = 0; i < 30; i++){
            new Thread(){
                public void run(){
                    System.out.println(MyObject.getInstance());
                }
            }.start();

        }
    }
}
