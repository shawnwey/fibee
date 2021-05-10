package xyz.xingwei.fibee.model.exception;

import lombok.Getter;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 16:00
 */
@Getter
public class SerializeException extends RuntimeException{
    private Type type;

    private SerializeException(Type type, String message) {
        super(type + message);
        this.type = type;
    }

    public static SerializeException serializeException(String message) {
        return new SerializeException(Type.serialize, message);
    }

    public static SerializeException deserializeException(String message) {
        return new SerializeException(Type.deserialize, message);
    }

    public enum Type {
        serialize("serialize"),
        deserialize("deserialize");

        String type;

        Type(String type) {
            this.type = type;
        }
    }
}
