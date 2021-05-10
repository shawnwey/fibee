package xyz.xingwei.fibee.remoting;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 10:59
 */
public class ClientEventHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端【channelActive】：消息已发送");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, netty!", CharsetUtil.UTF_8));
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        System.out.println("客户端收到：" + msg.toString(CharsetUtil.UTF_8));
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//    }
}
