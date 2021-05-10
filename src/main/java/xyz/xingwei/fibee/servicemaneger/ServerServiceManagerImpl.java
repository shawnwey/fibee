package xyz.xingwei.fibee.servicemaneger;

import xyz.xingwei.fibee.model.bo.ServiceProvider;
import xyz.xingwei.fibee.model.enums.ErrorInfoEnum;
import xyz.xingwei.fibee.model.exception.RpcException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xingwei.xyz
 * @date 2021/5/2 21:29
 */
public class ServerServiceManagerImpl implements ServerServiceManager {
    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    @Override
    public Object addService(ServiceProvider serviceProvider) {
        String serviceName = serviceProvider.getServiceName();
        return serviceMap.put(serviceName, serviceProvider.getProvider());
    }

    @Override
    public Object getService(String serviceKey) {
        Object service = serviceMap.get(serviceKey);
        if (service == null) {
            throw new RpcException(ErrorInfoEnum.SERVICE_NOT_FOUND);
        }
        return service;
    }

    @Override
    public Object removeService(String serviceKey) {
        return serviceMap.remove(serviceKey);
    }
}
