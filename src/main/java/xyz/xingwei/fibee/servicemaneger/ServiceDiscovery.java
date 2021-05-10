package xyz.xingwei.fibee.servicemaneger;

import xyz.xingwei.fibee.model.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 22:03
 */
public interface ServiceDiscovery {
    InetSocketAddress serviceDiscover(RpcRequest request);
}
