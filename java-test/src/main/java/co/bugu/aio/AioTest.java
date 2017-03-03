package co.bugu.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by user on 2017/3/3.
 */
public class AioTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        AioTest test = new AioTest();
//        test.test();
        test.callbackTest();

    }

    public void test() throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get("/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> future = channel.read(buffer, 0);
        Integer readNum = future.get();

        buffer.flip();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        decoder.decode(buffer, charBuffer, false);
        charBuffer.flip();
        String data = new String(charBuffer.array(), 0, charBuffer.limit());
        System.out.println("readNum: " + readNum);
        System.out.println(data);

    }

    public void callbackTest() throws IOException, InterruptedException {
        Path path = Paths.get("/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println(Thread.currentThread().getName() + " read success!");
                attachment.flip();


                CharBuffer charBuffer = CharBuffer.allocate(1024);
                CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                decoder.decode(attachment, charBuffer, false);

                charBuffer.flip();
                String data = new String(charBuffer.array(), 0, charBuffer.limit());
                System.out.println("读取到的数据： \n" + data);

            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("read error");
            }
        });
        while(true){
            System.out.println(Thread.currentThread().getName() + " sleep");
            Thread.sleep(1000);
        }
    }
}
