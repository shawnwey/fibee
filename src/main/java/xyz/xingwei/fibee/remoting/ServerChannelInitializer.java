package xyz.xingwei.fibee.remoting;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 10:12
 */
public class ServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new TcpController());
    }
}
