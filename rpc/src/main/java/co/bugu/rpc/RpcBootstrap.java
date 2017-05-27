package co.bugu.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by user on 2017/5/27.
 */
public class RpcBootstrap {
    public static void main(String[] args){
        new ClassPathXmlApplicationContext("spring.xml").start();
    }
}
