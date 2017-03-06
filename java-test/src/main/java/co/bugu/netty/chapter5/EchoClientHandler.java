package co.bugu.netty.chapter5;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by user on 2017/3/6.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int counter ;

    static final String ECHO_REQ = "Hi, daocers. welcome to netty.$_";

    public void channelActive(ChannelHandlerContext context){
        for(int i = 0; i < 10; i++){
            context.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg){
        System.out.println("this is " + ++counter + " times receive server: [" + msg + "] ");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context){
        context.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable){
        context.close();
    }


}
