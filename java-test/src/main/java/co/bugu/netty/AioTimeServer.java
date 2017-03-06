package co.bugu.netty;

/**
 * Created by user on 2017/3/6.
 * aio 实现
 */
public class AioTimeServer {
    public static void main(String[] args){
        int port = 8000;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
