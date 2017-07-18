package co.bugu.netty.importnew;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.AbstractChannelBuffer;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.util.concurrent.Executors;

/**
 * Created by QDHL on 2017/7/18.
 */
public class EchoServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap =
                new ServerBootstrap(new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()
                ));
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new EchoServerHandler());
            }
        });
    }
}
