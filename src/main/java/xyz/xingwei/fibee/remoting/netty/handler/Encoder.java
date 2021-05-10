package xyz.xingwei.fibee.remoting.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import xyz.xingwei.fibee.model.FibeeConst;
import xyz.xingwei.fibee.remoting.protocol.FibeeMessage;
import xyz.xingwei.fibee.remoting.session.Serializer;

/**
 * @author xingwei.xyz
 * @date 2021/5/4 17:50
 */
public class Encoder extends MessageToByteEncoder<FibeeMessage> {
    private final Serializer serializer;

    public Encoder(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, FibeeMessage msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.getMagicNum());
        out.writeByte(msg.getVersion());
        out.writeByte(msg.getMessageType());
        out.writeByte(msg.getSerializeProtocol());
        out.writeByte(msg.getCompressType());
        out.writeBytes(FibeeConst.RESERVED);

        // 数据序列化后写入
        byte[] bytes = serializer.serialize(msg.getData());
        out.writeInt(FibeeConst.HEAD_LENGTH + bytes.length);
        out.writeBytes(bytes);
    }
}
