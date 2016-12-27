package com.pinkbox.kacha.payment.service;


import com.pinkbox.kacha.payment.domain.wechat.WxNotifyRequest;

public interface IWxnotifyrequestService {
    int save(WxNotifyRequest wxnotifyrequest);

}
