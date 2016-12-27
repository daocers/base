package co.bugu.tes.service;


import co.bugu.tes.model.Paper;
import co.bugu.tes.model.Scene;
import co.bugu.tes.model.User;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IPaperService {
    int save(Paper paper);

    int updateById(Paper paper);

    int saveOrUpdate(Paper paper);

    int delete(Paper paper);

    Paper findById(Integer id);

    List<Paper> findAllByObject(Paper paper);

    PageInfo listByObject(Paper paper, PageInfo<Paper> pageInfo) throws Exception;

    //开场生成所有试卷。 scene中包含所有的场次信息，包含出题策略，考试人员等信息。
    boolean generateAllPaper(Scene scene);

    //适合为开场之前添加的考试用户出题，开场之后禁止添加用户
    boolean generatePaperForUser(Scene scene, User user);

    //禁止考试。此时需要把用户从场次关联表中的状态改为禁用
    boolean disabledUserOfScene(Scene scene, User user);

}
