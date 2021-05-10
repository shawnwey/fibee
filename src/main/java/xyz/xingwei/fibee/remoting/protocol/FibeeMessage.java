package xyz.xingwei.fibee.remoting.protocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.xingwei.fibee.model.FibeeConst;

/**
 * @author xingwei.xyz
 * @date 2021/5/4 18:25
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FibeeMessage {
    private byte[] magicNum;

    private byte version;

    private byte messageType;

    private byte compressType;

    private byte serializeProtocol;

    private Object data;

    public static FibeeMessage getMessage(byte messageType, Object data) {
        return FibeeMessage.builder()
                .magicNum(FibeeConst.MAGIC_NUM)
                .version(FibeeConst.VERSION)
                .messageType(messageType)
                .compressType(FibeeConst.FIB_COMPRESS)
                .serializeProtocol(FibeeConst.KRYO_SERIALIZE)
                .build();
    }
}
