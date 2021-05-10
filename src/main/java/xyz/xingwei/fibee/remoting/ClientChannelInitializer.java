package xyz.xingwei.fibee.remoting;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 10:56
 */
public class ClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new ClientEventHandler());
    }
}
