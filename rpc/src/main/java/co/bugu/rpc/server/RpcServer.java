package co.bugu.rpc.server;

import co.bugu.rpc.ServiceRegistry;
import co.bugu.rpc.annotation.RpcService;
import co.bugu.rpc.domain.RpcRequest;
import co.bugu.rpc.domain.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/5/27.
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {
    private Logger logger = LoggerFactory.getLogger(RpcServer.class);
    private String serverAdddress;
    private ServiceRegistry serviceRegistry;

    private Map<String, Object> handlerMap = new HashMap<>();//存放接口名和服务对象之间的映射关系

    public RpcServer(String serverAdddress){
        this.serverAdddress = serverAdddress;
    }

    public RpcServer(String serverAdddress, ServiceRegistry serviceRegistry){
        this.serverAdddress = serverAdddress;
        this.serviceRegistry = serviceRegistry;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                           channel.pipeline()
                                   .addLast(new RpcDecoder(RpcRequest.class))//将rpc请求进行解码 （为了处理请求）
                                   .addLast(new RpcEncoder(RpcResponse.class))//将rpc响应进行编码 （
                                   .addLast(new RpcHandler(handlerMap));//处理rpc请求
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            String[] array = serverAdddress.split(":");
            String host = array[0];
            Integer port = Integer.parseInt(array[1]);
            ChannelFuture future = bootstrap.bind(host, port).sync();
            logger.debug("server started on port {}", port);
            if(serviceRegistry != null){
                serviceRegistry.register(serverAdddress);//注册服务器地址
            }
            future.channel().closeFuture().sync();
        }catch (Exception e){
            logger.error("异常", e);
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);//获取所有带RpcService注解的类
        if(MapUtils.isNotEmpty(serviceBeanMap)){
            for(Object serviceBean : serviceBeanMap.values()){
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }
}
