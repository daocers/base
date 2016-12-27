package co.bugu.tes.service;


import co.bugu.tes.model.PaperPolicy;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IPaperPolicyService {
    int save(PaperPolicy paperpolicy);

    int updateById(PaperPolicy paperpolicy);

    int saveOrUpdate(PaperPolicy paperpolicy);

    int delete(PaperPolicy paperpolicy);

    PaperPolicy findById(Integer id);

    List<PaperPolicy> findAllByObject(PaperPolicy paperpolicy);

    PageInfo listByObject(PaperPolicy paperpolicy, PageInfo<PaperPolicy> pageInfo) throws Exception;

}
