package com.jijie.springcloud.controller;

import com.jijie.springcloud.entities.CommentResult;
import com.jijie.springcloud.entities.Payment;
import com.jijie.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>Description: </p>
 *
 * @author jijie
 * @Date 2021/5/17 11:11
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/create")
    public CommentResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if (result > 0){
            return new CommentResult(200,"插入数据库成功"+port,result);
        }
        return new CommentResult(444,"插入数据库失败"+port);
    }

    @GetMapping(value = "/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果："+result);
        if (result != null) {
            return new CommentResult(200,"查询成功，哈哈哈哈"+port,result);
        }
        return new CommentResult(444,"查询失败，哈哈哈哈，查询id"+port+id);

    }

    @GetMapping(value = "/lb")
    public String getPaymentLB()
    {
        return port;
    }

}
