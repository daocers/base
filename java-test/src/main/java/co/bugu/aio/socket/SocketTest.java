package co.bugu.aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by user on 2017/3/3.
 */
public class SocketTest {

    public void testClient() throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 8888)).get();
        ByteBuffer buffer = ByteBuffer.wrap("中文，你好。".getBytes());
        Future<Integer> future = channel.write(buffer);
        future.get();
        System.out.println("send ok");
    }


    public void testServer() throws IOException, InterruptedException {
        final AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open()
                .bind(new InetSocketAddress("0.0.0.0", 8888));
        channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(final AsynchronousSocketChannel client, Object attachment) {
                channel.accept(null, this);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        attachment.flip();
                        CharBuffer charBuffer = CharBuffer.allocate(1024);
                        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                        decoder.decode(attachment, charBuffer, false);
                        charBuffer.flip();
                        String data = new String(charBuffer.array(), 0, charBuffer.limit());
                        System.out.println("read data: " + data);
                        try{
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        System.out.println("read error");
                    }
                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("accept error");
            }
        });
        while(true){
            Thread.sleep(1000);
        }
    }
}
