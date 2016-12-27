package co.bugu.tes.service.impl;


import co.bugu.tes.model.QuestionPolicy;
import co.bugu.tes.service.IQuestionPolicyService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionPolicyServiceImpl implements IQuestionPolicyService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(QuestionPolicy questionpolicy) {
        return baseDao.insert("tes.questionpolicy.insert", questionpolicy);
    }

    @Override
    public int updateById(QuestionPolicy questionpolicy) {
        return baseDao.update("tes.questionpolicy.updateById", questionpolicy);
    }

    @Override
    public int saveOrUpdate(QuestionPolicy questionpolicy) {
        if(questionpolicy.getId() == null){
            return baseDao.insert("tes.questionpolicy.insert", questionpolicy);
        }else{
            return baseDao.update("tes.questionpolicy.updateById", questionpolicy);
        }
    }

    @Override
    public int delete(QuestionPolicy questionpolicy) {
        return baseDao.delete("tes.questionpolicy.deleteById", questionpolicy);
    }

    @Override
    public QuestionPolicy findById(Integer id) {
        return baseDao.selectOne("tes.questionpolicy.selectById", id);
    }

    @Override
    public List<QuestionPolicy> findAllByObject(QuestionPolicy questionpolicy) {
        return baseDao.selectList("tes.questionpolicy.listByObject", questionpolicy);
    }

    @Override
    public PageInfo listByObject(QuestionPolicy questionpolicy, PageInfo<QuestionPolicy> pageInfo) throws Exception {
        return baseDao.listByObject("tes.questionpolicy.listByObject", questionpolicy, pageInfo);
    }
}
