package com.pinkbox.kacha.payment.service.impl;


import co.bugu.framework.core.dao.BaseDao;
import com.pinkbox.kacha.payment.domain.wechat.UnifiedOrderRequest;
import com.pinkbox.kacha.payment.service.IUnifiedorderrequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnifiedorderrequestServiceImpl implements IUnifiedorderrequestService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(UnifiedOrderRequest unifiedorderrequest) {
        return baseDao.insert("pay.unifiedorderrequest.insert", unifiedorderrequest);
    }

    @Override
    public UnifiedOrderRequest selectById(String outTradeNo) {
        return baseDao.selectOne("pay.unifiedorderrequest.selectById", outTradeNo);
    }



}
