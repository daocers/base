package co.bugu.netty.importnew;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * Created by QDHL on 2017/7/18.
 */
public class EchoServerHandler extends SimpleChannelUpstreamHandler {
    @Override
    public void messageReceived(ChannelHandlerContext context, MessageEvent event){
        event.getChannel().write(event.getMessage());
    }
}
