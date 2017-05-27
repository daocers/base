package co.bugu.rpc.coder;

import co.bugu.framework.core.util.BuguProperties;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 2017/5/27.
 */
public class SerializationUtil {
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();
    private static Objenesis objenesis = new ObjenesisStd(true);
    private SerializationUtil(){

    }
    private static <T> Schema<T> getSchema(Class<T> cls){
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if(schema == null){
            schema = RuntimeSchema.createFrom(cls);
            if(schema != null){
                cachedSchema.put(cls,schema);
            }
        }
        return schema;
    }


    public static <T> byte[] serialize(T obj){
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer  LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try{
            S
        }
    }
}
