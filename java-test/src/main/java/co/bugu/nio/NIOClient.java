package co.bugu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by daocers on 2016/10/18.
 */
public class NIOClient {
    private Selector selector;

    public void initClient(String ip, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = Selector.open();

        channel.connect(new InetSocketAddress(ip, port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    public void listen() throws IOException {
        while(true){
            selector.select();
            Iterator iter = this.selector.selectedKeys().iterator();
            while(iter.hasNext()){
                SelectionKey key = (SelectionKey) iter.next();
                iter.remove();
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("向服务端发送了一条消息").getBytes()));
                    channel.register(this.selector, SelectionKey.OP_READ);

                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data, "utf-8").trim();
        System.out.println("客户端收到消息： " + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.initClient("localhost", 8000);
        client.listen();
    }
}
