package com.pinkbox.kacha.payment.service.impl;


import co.bugu.framework.core.dao.BaseDao;
import com.pinkbox.kacha.payment.domain.wechat.WxNotifyRequest;
import com.pinkbox.kacha.payment.service.IWxnotifyrequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxnotifyrequestServiceImpl implements IWxnotifyrequestService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(WxNotifyRequest wxnotifyrequest) {
        WxNotifyRequest tmp = baseDao.selectOne("pay.wxnotifyrequest.selectById", wxnotifyrequest);
        if(tmp != null){
            return 0;
        }
        return baseDao.insert("pay.wxnotifyrequest.insert", wxnotifyrequest);
    }

}
