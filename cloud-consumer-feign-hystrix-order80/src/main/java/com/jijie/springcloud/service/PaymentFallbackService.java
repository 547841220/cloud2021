package com.jijie.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author jijie
 * @Date 2021/5/17 11:11
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{


    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fallback -paymentInfo_OK,/(ćoć)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fallback -paymentInfo_OK,/(ćoć)/~~";
    }
}
