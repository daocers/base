package com.pinkbox.kacha.payment.service.impl;


import co.bugu.framework.core.dao.BaseDao;
import com.pinkbox.kacha.payment.domain.alipay.AliNotifyRequest;
import com.pinkbox.kacha.payment.service.IAlinotifyrequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlinotifyrequestServiceImpl implements IAlinotifyrequestService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(AliNotifyRequest alinotifyrequest) {
        AliNotifyRequest aliNotifyRequest = baseDao.selectOne("pay.alinotifyrequest.selectById", alinotifyrequest.getTrade_no());
        if(aliNotifyRequest == null){
            return baseDao.insert("pay.alinotifyrequest.insert", alinotifyrequest);
        }
        return 0;
    }

}
