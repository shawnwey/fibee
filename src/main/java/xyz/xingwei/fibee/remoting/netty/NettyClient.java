package xyz.xingwei.fibee.remoting.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import xyz.xingwei.fibee.Fibee;
import xyz.xingwei.fibee.model.FibeeConst;
import xyz.xingwei.fibee.model.dto.RpcRequest;
import xyz.xingwei.fibee.model.dto.RpcResponse;
import xyz.xingwei.fibee.model.enums.ErrorInfoEnum;
import xyz.xingwei.fibee.model.exception.RpcException;
import xyz.xingwei.fibee.remoting.ClientChannelInitializer;
import xyz.xingwei.fibee.remoting.TransmissionClient;
import xyz.xingwei.fibee.remoting.protocol.FibeeMessage;
import xyz.xingwei.fibee.servicemaneger.ServiceDiscovery;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 20:32
 */
@Slf4j
public class NettyClient extends TransmissionClient<RpcRequest, RpcResponse<?>> {

    private Bootstrap bootstrap;
    private final ServiceDiscovery serviceDiscovery;

    public NettyClient(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
        start();
    }

    private void start() {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(eventExecutors)
                .handler(new ClientChannelInitializer());
    }


    @Override
    public RpcResponse<?> sendMessage(RpcRequest request) throws RpcException {
        InetSocketAddress socketAddress = serviceDiscovery.serviceDiscover(request);
        Channel channel = getChannel(socketAddress);
        if (!channel.isActive()) {
            throw new RpcException(ErrorInfoEnum.CHANNEL_STATE_ILLEGAL);
        }

        // asynchronous sending
        CompletableFuture<RpcResponse<?>> completableFuture = new CompletableFuture<>();

        FibeeMessage fibee = FibeeMessage.getMessage(FibeeConst.MESSAGE_TYPE_REQUEST, request);
        channel.writeAndFlush(fibee).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                log.info("sendMessage success");
            } else {
                future.channel().close();
                completableFuture.completeExceptionally(future.cause());
                log.error("sendMessage error, cause: {}", future.cause().getMessage());
                future.cause().printStackTrace();
            }
        });
        try {
            // asynchronous receiving response
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("completableFuture failure");
            e.printStackTrace();
            throw new RpcException(ErrorInfoEnum.NET_DEFAULT_ERROR);
        }
    }

    public Channel getChannel(InetSocketAddress socketAddress) throws RpcException {
        // todo:netty connect pool
        Channel connect = null;
        try {
            connect = connect(socketAddress);
        } catch (ConnectException e) {
            log.error("connect fail: [{}]", socketAddress);
            e.printStackTrace();
            throw new RpcException(ErrorInfoEnum.NET_CONNECT_ERROR);
        }
        return connect;
    }

    public Channel connect(InetSocketAddress socketAddress) throws ConnectException {
        try {
            log.info("========== client connecting: [{}:{}] ============", socketAddress.getHostString(), socketAddress.getPort());
            ChannelFuture channelFuture = bootstrap.connect(socketAddress.getHostString(), socketAddress.getPort()).sync();
            return channelFuture.sync().channel();
        } catch (InterruptedException e) {
            throw new ConnectException();
        }
    }
}
