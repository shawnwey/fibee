package xyz.xingwei.fibee.servicemaneger;

import xyz.xingwei.fibee.model.bo.ServiceProvider;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 21:08
 */
public interface ServerServiceManager {

    Object addService(ServiceProvider serviceProvider);

    Object getService(String serviceKey);

    Object removeService(String serviceKey);
}
