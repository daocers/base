package co.bugu.rpc.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.lang.SerializationUtils;

/**
 * Created by user on 2017/5/27.
 */
public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass){
        this.genericClass = genericClass;
    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        if(genericClass.isInstance(in)){
            byte[] data = SerializationUtils.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
