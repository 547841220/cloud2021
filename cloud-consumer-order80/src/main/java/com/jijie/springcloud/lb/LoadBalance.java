package com.jijie.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author jijie
 * @Date 2021/5/17 11:11
 */
public interface LoadBalance
{
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
