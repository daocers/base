package com.pinkbox.kacha.payment.service;


import com.pinkbox.kacha.payment.domain.alipay.AliNotifyRequest;

public interface IAlinotifyrequestService {
    int save(AliNotifyRequest alinotifyrequest);

}
