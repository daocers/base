package co.bugu.netty.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by user on 2017/3/6.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    int counter = 0;
    @Override
    public void channelRead(ChannelHandlerContext context, Object msg){
        String body = (String) msg;
        System.out.println("this is " + ++counter + " times receive clinet:[ " + body + " ]");
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        context.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable){
        throwable.printStackTrace();
        context.close();
    }
}
