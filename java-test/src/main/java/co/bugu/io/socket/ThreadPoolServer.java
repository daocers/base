package co.bugu.io.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 2017/3/2.try{
 *
 * }
 */
public class ThreadPoolServer {
    public static void main(String[] args){
        ServerSocket server;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try{
            server = new ServerSocket(10001);
            Socket socket = null;
            while(true){
                System.out.println("服务端等待客户端发起连接请求");
                socket = server.accept();
                System.out.println("客户端想服务端发起了连接请求，连接成功");
                executor.execute(new Handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
