package xyz.xingwei.fibee.proxy;

import lombok.extern.slf4j.Slf4j;
import xyz.xingwei.fibee.model.dto.RpcRequest;
import xyz.xingwei.fibee.model.dto.RpcResponse;
import xyz.xingwei.fibee.remoting.netty.NettyClient;
import xyz.xingwei.fibee.remoting.TransmissionClient;
import xyz.xingwei.fibee.servicemaneger.zookeeper.ZkServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 20:24
 */
@Slf4j
public class RpcInvocationHandler implements InvocationHandler {

    TransmissionClient<RpcRequest, RpcResponse<?>> transmissionClient = new NettyClient(new ZkServiceDiscovery());

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getCanonicalName())
                .methodName(method.getName())
                .returnType(method.getReturnType())
                .argTypes(method.getParameterTypes())
                .args(args)
                .build();

        RpcResponse<?> response = transmissionClient.sendMessage(rpcRequest);

        return response.getData();
    }

}
