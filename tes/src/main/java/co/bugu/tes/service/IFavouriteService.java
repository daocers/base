package co.bugu.tes.service;


import co.bugu.tes.model.Favourite;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IFavouriteService {
    int save(Favourite favourite);

    int updateById(Favourite favourite);

    int saveOrUpdate(Favourite favourite);

    int delete(Favourite favourite);

    Favourite findById(Integer id);

    List<Favourite> findAllByObject(Favourite favourite);

    PageInfo listByObject(Favourite favourite, PageInfo<Favourite> pageInfo) throws Exception;

}
