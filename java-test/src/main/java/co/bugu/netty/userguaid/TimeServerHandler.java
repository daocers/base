package co.bugu.netty.userguaid;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by user on 2017/3/7.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext context){
        final ByteBuf time = context.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000l + 2208988800l));
        final ChannelFuture future = context.writeAndFlush(time);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                assert channelFuture == future;
                context.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable){
        throwable.printStackTrace();
        context.close();
    }
}
