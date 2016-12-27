package com.pinkbox.kacha.payment.service.impl;


import co.bugu.framework.core.dao.BaseDao;
import com.pinkbox.kacha.payment.domain.wechat.PaymentNotify;
import com.pinkbox.kacha.payment.service.IPaymentnotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentnotifyServiceImpl implements IPaymentnotifyService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(PaymentNotify paymentnotify) {
        return baseDao.insert("pay.paymentnotify.insert", paymentnotify);
    }

}