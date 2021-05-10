package xyz.xingwei.fibee.servicemaneger.zookeeper;

import xyz.xingwei.fibee.servicemaneger.ServiceRegister;

import java.net.InetSocketAddress;

/**
 * @author xingwei.xyz
 * @date 2021/5/3 12:13
 */
public class ZkServiceRegister implements ServiceRegister {
    @Override
    public Boolean register(String serviceName, InetSocketAddress socketAddress) {
        return null;
    }
}
