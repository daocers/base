package co.bugu.tes.service;


import co.bugu.tes.model.Profile;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IProfileService {
    int save(Profile profile);

    int updateById(Profile profile);

    int saveOrUpdate(Profile profile);

    int delete(Profile profile);

    Profile findById(Integer id);

    List<Profile> findAllByObject(Profile profile);

    PageInfo listByObject(Profile profile, PageInfo<Profile> pageInfo) throws Exception;

}
