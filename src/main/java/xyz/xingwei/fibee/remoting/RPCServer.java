package xyz.xingwei.fibee.remoting;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 10:01
 */
public class RPCServer {

    Integer port;

    public RPCServer(Integer port) {
        this.port = port;
    }

    public void start() {
        // 引导器
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer());

        try {
            ChannelFuture bindFuture = serverBootstrap.bind(port).sync();

            System.out.println("============ server started =============");

            bindFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            System.out.println("======== server channel closed ==========");
        }
    }

    public static void main(String[] args) {
        new RPCServer(7788).start();
    }
}
