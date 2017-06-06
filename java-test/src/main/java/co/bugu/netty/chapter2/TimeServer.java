package co.bugu.netty.chapter2;

/**
 * Created by user on 2017/6/6.
 */
public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        MultiplexerTimeserver timeserver = new MultiplexerTimeServer(port);
        new Thread(TimeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
