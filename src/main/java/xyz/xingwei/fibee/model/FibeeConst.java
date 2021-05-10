package xyz.xingwei.fibee.model;

/**
 * @author xingwei.xyz
 * @date 2021/5/5 11:01
 */
public class FibeeConst {

    public static final byte[] MAGIC_NUM = {'f', 'i', 'b'};
    public static final byte VERSION = 1;

    public static final byte MESSAGE_TYPE_HEARTBEAT = 1;

    public static final byte MESSAGE_TYPE_REQUEST = 2;

    public static final byte FIB_COMPRESS = 0;

    public static final byte KRYO_SERIALIZE = 5;

    public static final byte[] RESERVED = new byte[4];

    // 12 allocated bytes + 4 reserved bytes
    public static final int HEAD_LENGTH = 16;

    public static final int MAX_FRAME_LENGTH = 16 * 1024 * 1024;
}
