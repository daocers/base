package co.bugu.serialization;

import com.alibaba.fastjson.JSON;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by user on 2017/6/1.
 */
public class ProtostuffTest {
    public static void main(String[] args){
        User user = new User("name", "password", 12L);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        Schema<User> schema = RuntimeSchema.createFrom(User.class);
        schema = SerializationUtil.getSchema(User.class);

        byte[] bytes = ProtostuffIOUtil.toByteArray(user, schema, buffer);
        System.out.println(new String(bytes));
        User t = new User();
        ProtostuffIOUtil.mergeFrom(bytes, t, schema);
        System.out.println(JSON.toJSONString(t));
    }

}
