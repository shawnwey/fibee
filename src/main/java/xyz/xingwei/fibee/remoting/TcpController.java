package xyz.xingwei.fibee.remoting;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 10:24
 */
public class TcpController extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = null;
        if (msg instanceof ByteBuf) {
            in = (ByteBuf) msg;
        }

        System.out.println("服务端接收到：" + in.toString(CharsetUtil.UTF_8));
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(
                in.toString(CharsetUtil.UTF_8) + "-------server", CharsetUtil.UTF_8));
    }
}
