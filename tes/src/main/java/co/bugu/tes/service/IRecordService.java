package co.bugu.tes.service;


import co.bugu.tes.model.Record;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IRecordService {
    int save(Record record);

    int updateById(Record record);

    int saveOrUpdate(Record record);

    int delete(Record record);

    Record findById(Integer id);

    List<Record> findAllByObject(Record record);

    PageInfo listByObject(Record record, PageInfo<Record> pageInfo) throws Exception;

}
