package com.pinkbox.kacha.payment.service.impl;


import co.bugu.framework.core.dao.BaseDao;
import com.pinkbox.kacha.payment.domain.wechat.UnifiedOrderResponse;
import com.pinkbox.kacha.payment.service.IUnifiedorderresponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnifiedorderresponseServiceImpl implements IUnifiedorderresponseService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(UnifiedOrderResponse unifiedorderresponse) {
        return baseDao.insert("pay.unifiedorderresponse.insert", unifiedorderresponse);
    }

    @Override
    public UnifiedOrderResponse selectById(String prepayId) {
        return baseDao.selectOne("pay.unifiedorderresponse.selectById", prepayId);
    }

}
