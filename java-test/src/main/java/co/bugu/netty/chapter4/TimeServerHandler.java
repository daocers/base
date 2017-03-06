package co.bugu.netty.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * Created by user on 2017/3/6.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        String body = (String) msg;
        System.out.println("the time server receive order: " + body +
                " ; the counter is: " + ++counter);
        String time = "query time order".equalsIgnoreCase(body) ? new Date().toString() : "bad order";
        ByteBuf resp = Unpooled.copiedBuffer(time.getBytes());
        context.writeAndFlush(resp);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable){
        context.close();
    }

}
