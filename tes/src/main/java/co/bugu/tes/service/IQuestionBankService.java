package co.bugu.tes.service;


import co.bugu.tes.model.QuestionBank;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IQuestionBankService {
    int save(QuestionBank questionbank);

    int updateById(QuestionBank questionbank);

    int saveOrUpdate(QuestionBank questionbank);

    int delete(QuestionBank questionbank);

    QuestionBank findById(Integer id);

    List<QuestionBank> findAllByObject(QuestionBank questionbank);

    PageInfo listByObject(QuestionBank questionbank, PageInfo<QuestionBank> pageInfo) throws Exception;

}
