package co.bugu.threadLearn;

/**
 * Created by daocers on 2016/11/4.
 */
public class ALogin extends Thread {
    @Override
    public void run(){
        LoginServlet.doPost("a", "aa");
    }
}
