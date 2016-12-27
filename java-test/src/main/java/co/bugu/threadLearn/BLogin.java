package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/4.
 */
public class BLogin extends Thread {
    @Override
    public void run(){
        LoginServlet.doPost("b", "bb");
    }

    public static void main(String[] args){
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
