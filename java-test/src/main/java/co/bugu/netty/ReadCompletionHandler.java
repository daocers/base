package co.bugu.netty;

import co.bugu.framework.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.BOFRecord;

import javax.lang.model.element.Element;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * Created by user on 2017/3/6.
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel channel;
    public ReadCompletionHandler(AsynchronousSocketChannel result) {
        this.channel = result;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        try{
            String req = new String(bytes, "UTF-8");
            System.out.println("the time server receive order : " + req);
            String time = "query time order".equalsIgnoreCase(req) ? new Date().toString(): "bad order";
            doWrite(time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doWrite(String time) {
        if(StringUtils.isNotEmpty(time)){
            byte[] bytes = time.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if(attachment.hasRemaining()){
                        channel.write(attachment, attachment, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try{
                        channel.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try{
            this.channel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
