package co.bugu.rpc.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.maven.surefire.shade.org.apache.commons.lang.SerializationUtils;

import java.util.List;

/**
 * Created by user on 2017/5/27.
 * 解码器
 */
public class RpcDecoder extends ByteToMessageDecoder {
    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass){
        this.genericClass = genericClass;
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() < 4){
            return ;
        }
        byteBuf.markReaderIndex();
        int dataLength = byteBuf.readInt();
        if(dataLength < 0){
            channelHandlerContext.close();
        }
        if(byteBuf.readableBytes() < dataLength){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);

        Object obj = SerializationUtils.deserialize(data, genericClass);
        list.add(obj);

    }
}
