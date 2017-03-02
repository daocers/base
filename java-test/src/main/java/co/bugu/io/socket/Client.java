package co.bugu.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by user on 2017/3/2.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 10001);
        client.setSoTimeout(10000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //获取键盘输入
        PrintStream out = new PrintStream(client.getOutputStream());
        //获取socket的输出流， 用来发送数据到服务端
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));

        //获取socket的输入流，用来接收从服务端发送过来的数据
        boolean flag = true;
        while(flag){
            System.out.println("输入信息： ");
            String str = input.readLine();
            out.println(str);
            if("byte".equals(str)){
                flag = false;
            }else{
                try{
                    String echo = buf.readLine();
                    System.out.println(echo);
                }catch (SocketTimeoutException e){
                    System.out.println("Time out, no response");
                }
            }
        }

        input.close();
        if(client != null){
            client.close();
        }
    }
}
