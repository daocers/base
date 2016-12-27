package co.bugu.tes.service;


import co.bugu.tes.model.Trade;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface ITradeService {
    int save(Trade trade);

    int updateById(Trade trade);

    int saveOrUpdate(Trade trade);

    int delete(Trade trade);

    Trade findById(Integer id);

    List<Trade> findAllByObject(Trade trade);

    PageInfo listByObject(Trade trade, PageInfo<Trade> pageInfo) throws Exception;

}
