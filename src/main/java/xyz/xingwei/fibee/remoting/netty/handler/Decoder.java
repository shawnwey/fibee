package xyz.xingwei.fibee.remoting.netty.handler;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import xyz.xingwei.fibee.model.FibeeConst;

/**
 * @author xingwei.xyz
 * @date 2021/5/5 11:19
 */
@Slf4j
public class Decoder extends LengthFieldBasedFrameDecoder {
    public Decoder() {
        this(FibeeConst.MAX_FRAME_LENGTH, FibeeConst.HEAD_LENGTH - 4, 4, FibeeConst.HEAD_LENGTH, 0);
    }

    /**
     * LengthFieldBasedFrameDecoder: resolve unpacking package and s
     * @param maxFrameLength
     * @param lengthFieldOffset
     * @param lengthFieldLength
     * @param lengthAdjustment: length compensation value to add from lengthField
     * @param initialBytesToStrip: start position of frame after strip
     */
    public Decoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
