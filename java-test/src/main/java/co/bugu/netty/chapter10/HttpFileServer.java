package co.bugu.netty.chapter10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by user on 2017/3/7.
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "/code";

    public void run(final int port, final String url){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            channel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            channel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            channel.pipeline().addLast("http_chunked", new ChunkedWriteHandler());
//                            channel.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
                        }
                    });
            ChannelFuture future = bootstrap.bind("127.0.0.1", port).sync();
            System.out.println("http 文件服务器启动， 网址是： http://127.0.0.1:" +  port + url);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args){
        int port = 8000;
        String url = DEFAULT_URL;
        new HttpFileServer().run(port, url);
    }
}
