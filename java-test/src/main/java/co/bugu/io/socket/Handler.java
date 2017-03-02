package co.bugu.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by user on 2017/3/2.
 */
public class Handler implements Runnable {
    private Socket client;
    PrintStream out;
    BufferedReader buf;

    public Handler(Socket client){
        this.client = client;
        try{
            out = new PrintStream(client.getOutputStream());
            buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try{
            boolean flag = true;
            while(flag){
                String str = buf.readLine();
                if(str == null || "".equals(str)){
                    flag = false;
                }else {
                    if("bye".equals(str)){
                        flag = false;
                    }else{
                        System.out.println(Thread.currentThread().getName() + "服务器从客户端接受的数据： " + str);
                        out.println("echo: " + str);
                    }
                }
            }
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
