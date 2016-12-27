package co.bugu.tes.service;


import co.bugu.tes.model.PropertyItem;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IPropertyItemService {
    int save(PropertyItem propertyitem);

    int updateById(PropertyItem propertyitem);

    int saveOrUpdate(PropertyItem propertyitem);

    int delete(PropertyItem propertyitem);

    PropertyItem findById(Integer id);

    List<PropertyItem> findAllByObject(PropertyItem propertyitem);

    PageInfo listByObject(PropertyItem propertyitem, PageInfo<PropertyItem> pageInfo) throws Exception;

}
