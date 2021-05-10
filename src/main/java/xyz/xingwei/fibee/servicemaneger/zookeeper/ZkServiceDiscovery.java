package xyz.xingwei.fibee.servicemaneger.zookeeper;

import xyz.xingwei.fibee.model.dto.RpcRequest;
import xyz.xingwei.fibee.servicemaneger.ServiceDiscovery;

import java.net.InetSocketAddress;

/**
 * @author xingwei.xyz
 * @date 2021/5/3 12:14
 */
public class ZkServiceDiscovery implements ServiceDiscovery {
    @Override
    public InetSocketAddress serviceDiscover(RpcRequest request) {
        return null;
    }
}
