package co.bugu.netty.chapter5;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by daocers on 2017/3/6.
 */
public class FixEchoSererHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext context, Object msg){
        System.out.println("received client: " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable){
        throwable.printStackTrace();
        context.close();
    }
}
