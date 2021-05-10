package xyz.xingwei.fibee.remoting;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 10:47
 */
public class RpcClientService {

    private Bootstrap bootstrap;

    private EventLoopGroup eventExecutors;

    public RpcClientService() {
        this.start();
    }

    private RpcClientService start() {
        eventExecutors = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(eventExecutors)
                .handler(new ClientChannelInitializer());
        return this;
    }

    public void connect(String host, Integer port) {
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            System.out.println("========== client connected ============");
            Channel channel = channelFuture.channel();
            System.out.println("" + channel.remoteAddress() + " isWritable: " + channel.isWritable());
            sendMsgLoop(channelFuture.channel());
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
            System.out.println("======== client channel closed ==========");
        }
    }

    public static void main(String[] args) {
        new RpcClientService().connect("localhost", 7788);
    }

    private void sendMsgLoop(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String msg = scanner.nextLine();
            channel.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        }
    }
}
