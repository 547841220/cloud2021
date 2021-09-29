package com.jijie.springcloud.service;

import com.jijie.springcloud.entities.Payment;

/**
 * <p>Description: </p>
 *
 * @author jijie
 * @Date 2021/5/17 11:11
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
