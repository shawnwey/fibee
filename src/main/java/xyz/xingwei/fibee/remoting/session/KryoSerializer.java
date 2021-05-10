package xyz.xingwei.fibee.remoting.session;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;
import xyz.xingwei.fibee.model.dto.RpcRequest;
import xyz.xingwei.fibee.model.dto.RpcResponse;
import xyz.xingwei.fibee.model.exception.SerializeException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 18:33
 */
@Slf4j
public class KryoSerializer implements Serializer {

    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        // 默认必须注册所有的要序列化的类
        kryo.register(RpcRequest.class);
        kryo.register(String[].class);
        kryo.register(Class[].class);
        kryo.register(Class.class);
        kryo.register(Object[].class);

        kryo.register(RpcResponse.class);

//        kryo.setRegistrationRequired(false);
        return kryo;
    });

    @Override
    public byte[] serialize(Object target) {
        try {
            log.info("serializing: [{}]", target);
            Kryo kryo = kryoThreadLocal.get();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Output output = new Output(outputStream);

            kryo.writeObject(output, target);
            return output.toBytes();
        } catch (Exception e) {
            throw SerializeException.serializeException(e.toString());
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try {
            Kryo kryo = kryoThreadLocal.get();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            Input input = new Input(inputStream);
            return kryo.readObject(input, clazz);
        } catch (Exception e) {
            throw SerializeException.deserializeException(clazz.getCanonicalName());
        }
    }
}
