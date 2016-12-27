package co.bugu.tes.service.impl;


import co.bugu.tes.model.QuestionMetaInfo;
import co.bugu.tes.service.IQuestionMetaInfoService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionMetaInfoServiceImpl implements IQuestionMetaInfoService {
    @Autowired
    BaseDao baseDao;

    @Override
    public int save(QuestionMetaInfo questionmetainfo) {
        return baseDao.insert("tes.questionmetainfo.insert", questionmetainfo);
    }

    @Override
    public int updateById(QuestionMetaInfo questionmetainfo) {
        return baseDao.update("tes.questionmetainfo.updateById", questionmetainfo);
    }

    @Override
    public int saveOrUpdate(QuestionMetaInfo questionmetainfo) {
        if(questionmetainfo.getId() == null){
            return baseDao.insert("tes.questionmetainfo.insert", questionmetainfo);
        }else{
            return baseDao.update("tes.questionmetainfo.updateById", questionmetainfo);
        }
    }

    @Override
    public int saveOrUpdate(QuestionMetaInfo questionmetainfo, List<Map<String, Integer>> list) {
        if(questionmetainfo.getId() == null){
            baseDao.insert("tes.questionmetainfo.insert", questionmetainfo);
        }else{
            baseDao.update("tes.questionmetainfo.updateById", questionmetainfo);
        }
        Integer quesMetaInfo = questionmetainfo.getId();

        Map<String, Integer> data = new HashMap<>();
        data.put("metaInfoId", quesMetaInfo);
        baseDao.delete("tes.questionmetainfo.deleteProperty", data);

        for(Map<String, Integer> map : list){
            map.put("metaInfoId", quesMetaInfo);
            baseDao.insert("tes.questionmetainfo.addProperty", map);
        }

        return 0;
    }

    @Override
    public int delete(QuestionMetaInfo questionmetainfo) {
        return baseDao.delete("tes.questionmetainfo.deleteById", questionmetainfo);
    }

    @Override
    public QuestionMetaInfo findById(Integer id) {
        return baseDao.selectOne("tes.questionmetainfo.selectById", id);
    }

    @Override
    public List<QuestionMetaInfo> findAllByObject(QuestionMetaInfo questionmetainfo) {
        return baseDao.selectList("tes.questionmetainfo.listByObject", questionmetainfo);
    }

    @Override
    public PageInfo listByObject(QuestionMetaInfo questionmetainfo, PageInfo<QuestionMetaInfo> pageInfo) throws Exception {
        return baseDao.listByObject("tes.questionmetainfo.listByObject", questionmetainfo, pageInfo);
    }

    @Override
    public void addProperty(Map<String, String> map) {
        baseDao.insert("tes.questionmetainfo.addProperty", map);
    }

    @Override
    public void deleteProperty(Map<String, String> map) {
        baseDao.delete("tes.questionmetainfo.deleteProperty", map);
    }
}
