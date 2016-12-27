package com.pinkbox.kacha.payment.service.impl;

import co.bugu.framework.core.dao.BaseDao;
import com.pinkbox.kacha.payment.domain.wechat.WxJsResponse;
import com.pinkbox.kacha.payment.service.IWxjsresponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxjsresponseServiceImpl implements IWxjsresponseService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(WxJsResponse wxjsresponse) {
        return baseDao.insert("pay.wxjsresponse.insert", wxjsresponse);
    }

}
