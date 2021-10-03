package com.jijie.springcloud.controller;

import com.jijie.springcloud.entities.CommentResult;
import com.jijie.springcloud.entities.Payment;
import com.jijie.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author jijie
 * @Date 2021/5/17 11:11
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalance loadBalance;



    @GetMapping("/payment/create")
    public CommentResult<Payment> create(Payment payment)
    {
        log.info("新增开始：",payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommentResult.class);
    }

    @GetMapping("payment/get/{id}")
    public CommentResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        log.info("查询开始,查询id",id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommentResult.class);
    }

    @GetMapping("payment/getForEntity/{id}")
    public CommentResult<Payment> getPayment2(@PathVariable("id") Long id)
    {
        log.info("查询开始,查询id",id);
        ResponseEntity<CommentResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode().toString());
            return entity.getBody();
        }
        return new CommentResult<>(444,"操作失败");

    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size()<=0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalance.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
