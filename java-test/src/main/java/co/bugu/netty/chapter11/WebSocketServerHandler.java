package co.bugu.netty.chapter11;

import com.alibaba.rocketmq.shade.io.netty.util.CharsetUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import sun.rmi.runtime.Log;

import java.util.Date;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

/**
 * Created by user on 2017/3/7.
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext context, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(context, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(context, (WebSocketFrame) msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.flush();
    }


    private void handleWebSocketFrame(ChannelHandlerContext context, WebSocketFrame frame) {
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(context.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        if(frame instanceof PingWebSocketFrame){
            context.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return ;
        }
        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()
            ));
        }

        String request = ((TextWebSocketFrame)frame).text();
        context.channel().write(new TextWebSocketFrame(request + ", 欢迎使用netty websocket 服务，现在时刻： " + new Date()));
    }

    private void handleHttpRequest(ChannelHandlerContext context, FullHttpRequest msg) {
        if (!msg.getDecoderResult().isSuccess() || (!"websocket".equals(msg.headers().get("Upgrade")))) {
            sendHttpResponse(context, msg,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:8080/websocket", null, false);
        handshaker = wsFactory.newHandshaker(msg);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(context.channel());
        }else{
            handshaker.handshake(context.channel(), msg);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest req, DefaultFullHttpResponse res) {
        if(res.getStatus().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            setContentLength(res, res.content().readableBytes());

        }
        ChannelFuture future = context.channel().writeAndFlush(res);
        if(!isKeepAlive(req) || res.getStatus().code() != 200){
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable){
        throwable.printStackTrace();
        context.close();
    }

}
