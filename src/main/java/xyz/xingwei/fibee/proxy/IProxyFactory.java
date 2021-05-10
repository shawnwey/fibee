package xyz.xingwei.fibee.proxy;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 20:22
 */
public interface IProxyFactory {

    <T> T getProxy(Class<?> clazz);
}
