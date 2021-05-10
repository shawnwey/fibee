package xyz.xingwei.fibee.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 20:20
 */
@Slf4j
public class ProxyFactory implements IProxyFactory {

    @Override
    public <T> T getProxy(Class<?> clazz) {
        log.info("generate proxy object of [{}]", clazz.getCanonicalName());
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcInvocationHandler());
    }
}
