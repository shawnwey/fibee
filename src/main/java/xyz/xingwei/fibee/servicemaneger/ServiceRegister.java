package xyz.xingwei.fibee.servicemaneger;

import java.net.InetSocketAddress;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 22:02
 */
public interface ServiceRegister {
    Boolean register(String serviceName, InetSocketAddress socketAddress);
}
