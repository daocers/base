package co.bugu.netty.userguaid;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by user on 2017/3/7.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext context, Object msg){
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println(new String(bytes));
        context.writeAndFlush(bytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable){
        throwable.printStackTrace();
        context.close();
    }


}
