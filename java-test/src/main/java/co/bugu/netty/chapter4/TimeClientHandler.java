package co.bugu.netty.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by user on 2017/3/6.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private int counter;
    private byte[] req;

    public TimeClientHandler() {
        req = ("query time order" + System.getProperty("line.separator")).getBytes();

    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            context.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        String body = (String) msg;
        System.out.println("Now is : " + body + " ; the counter is: " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        context.close();

    }
}