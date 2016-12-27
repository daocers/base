package co.bugu.tes.service.impl;


import co.bugu.tes.model.Trade;
import co.bugu.tes.service.ITradeService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements ITradeService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(Trade trade) {
        return baseDao.insert("tes.trade.insert", trade);
    }

    @Override
    public int updateById(Trade trade) {
        return baseDao.update("tes.trade.updateById", trade);
    }

    @Override
    public int saveOrUpdate(Trade trade) {
        if(trade.getId() == null){
            return baseDao.insert("tes.trade.insert", trade);
        }else{
            return baseDao.update("tes.trade.updateById", trade);
        }
    }

    @Override
    public int delete(Trade trade) {
        return baseDao.delete("tes.trade.deleteById", trade);
    }

    @Override
    public Trade findById(Integer id) {
        return baseDao.selectOne("tes.trade.selectById", id);
    }

    @Override
    public List<Trade> findAllByObject(Trade trade) {
        return baseDao.selectList("tes.trade.listByObject", trade);
    }

    @Override
    public PageInfo listByObject(Trade trade, PageInfo<Trade> pageInfo) throws Exception {
        return baseDao.listByObject("tes.trade.listByObject", trade, pageInfo);
    }
}
