package com.pinkbox.kacha.payment.service;


import com.pinkbox.kacha.payment.domain.wechat.UnifiedOrderResponse;

public interface IUnifiedorderresponseService {
    int save(UnifiedOrderResponse unifiedorderresponse);

    UnifiedOrderResponse selectById(String outTradeNo);
}
