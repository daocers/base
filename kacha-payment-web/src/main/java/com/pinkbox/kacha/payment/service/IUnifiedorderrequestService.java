package com.pinkbox.kacha.payment.service;


import com.pinkbox.kacha.payment.domain.wechat.UnifiedOrderRequest;

public interface IUnifiedorderrequestService {
    int save(UnifiedOrderRequest unifiedorderrequest);

    UnifiedOrderRequest selectById(String outTradeNo);
}
