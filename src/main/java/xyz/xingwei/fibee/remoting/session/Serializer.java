package xyz.xingwei.fibee.remoting.session;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 15:49
 */
public interface Serializer {
    byte[] serialize(Object target);

    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
