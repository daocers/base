package co.bugu.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by user on 2017/3/2.
 */
public class TestFrag {
    private void test() throws Exception {
        FileInputStream inputStream = new FileInputStream("/test.txt");
        FileChannel c = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        c.read(buffer);


        FileOutputStream out = new FileOutputStream("/test.txt");
        FileChannel channel = out.getChannel();
        buffer = ByteBuffer.allocate(1024);
        buffer.put("something you like".getBytes("utf-8"));
        buffer.flip();
        channel.write(buffer);


        FileInputStream in = new FileInputStream("/src");
        FileChannel fcin = in.getChannel();
        FileOutputStream os = new FileOutputStream("/target");
        FileChannel fcout = in.getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        while(true){
            buffer.clear();
            int r1 = fcin.read(buffer);
            if(r1 == -1){
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        };



        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {header, body};
        channel.read(buffers);

        channel.write(buffers);

    }
}
