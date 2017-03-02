package co.bugu.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by user on 2017/3/2.
 */
public class Test {
    int port = 10000;

    public void test() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);

                    clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    handleRead(key);
                }
                if (key.isWritable() && key.isValid()) {
                    handleWrite(key);
                }
                if (key.isConnectable()) {
                    System.out.println("连接成功。。。");
                }
            }
        }
    }

    private void handleWrite(SelectionKey key) {
    }

    private void handleRead(SelectionKey key) {
    }


    /**
     * 非阻塞的服务端实现
     * @throws Exception
     */
    public void test2() throws Exception {
        // 创建一个选择器，可用close()关闭，isOpen()表示是否处于打开状态，他不隶属于当前线程
        Selector selector = Selector.open();
        // 创建ServerSocketChannel，并把它绑定到指定端口上
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("127.0.0.1", 7777));
        // 设置为非阻塞模式, 这个非常重要
        server.configureBlocking(false);
        // 在选择器里面注册关注这个服务器套接字通道的accept事件
        // ServerSocketChannel只有OP_ACCEPT可用，OP_CONNECT,OP_READ,OP_WRITE用于SocketChannel 【非常重要】
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 测试等待事件发生，分为直接返回的selectNow()和阻塞等待的select()，另外也可加一个参数表示阻塞超时
            // 停止阻塞的方法有两种: 中断线程和selector.wakeup()，有事件发生时，会自动的wakeup()
            // 方法返回为select出的事件数(参见后面的注释有说明这个值为什么可能为0).
            // 另外务必注意一个问题是，当selector被select()阻塞时，其他的线程调用同一个selector的register也会被阻塞到select返回为止
            // select操作会把发生关注事件的Key加入到selectionKeys中(只管加不管减)
            if (selector.select() == 0) { //
                continue;
            }

            // 获取发生了关注时间的Key集合，每个SelectionKey对应了注册的一个通道
            Set<SelectionKey> keys = selector.selectedKeys();
            // 多说一句selector.keys()返回所有的SelectionKey(包括没有发生事件的)
            for (SelectionKey key : keys) {
                /***
                 * / OP_ACCEPT 这个只有ServerSocketChannel才有可能触发
                 * */
                if (key.isAcceptable()) {
                    // 得到与客户端的套接字通道
                    SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                    // 同样设置为非阻塞模式
                    channel.configureBlocking(false);
                    // 同样将于客户端的通道在selector上注册，OP_READ对应可读事件(对方有写入数据),可以通过key获取关联的选择器
                    channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                // OP_READ 有数据可读
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 得到附件，就是上面SocketChannel进行register的时候的第三个参数,可为随意Object
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    // 读数据 这里就简单写一下，实际上应该还是循环读取到读不出来为止的
                    channel.read(buffer);
                    // 改变自身关注事件，可以用位或操作|组合时间
                    key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }
                // OP_WRITE 可写状态 这个状态通常总是触发的，所以只在需要写操作时才进行关注
                if (key.isWritable()) {
                    // 写数据掠过，可以自建buffer，也可用附件对象(看情况),注意buffer写入后需要flip
                    // ......
                    // 写完就吧写状态关注去掉，否则会一直触发写事件
                    key.interestOps(SelectionKey.OP_READ);
                }

                // 由于select操作只管对selectedKeys进行添加，所以key处理后我们需要从里面把key去掉
                keys.remove(key);
            }
        }
    }


    /**
     * 非阻塞的客户端实现
     */

    public void test3() throws IOException {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1", 10000));
        channel.register(selector, SelectionKey.OP_CONNECT);
        while(true){
            Set<SelectionKey> keys = selector.selectedKeys();
            for(SelectionKey key: keys){
                if(key.isConnectable()){
                    if(channel.finishConnect())
                    {
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        }

    }
}
