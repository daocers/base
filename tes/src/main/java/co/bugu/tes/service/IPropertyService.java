package co.bugu.tes.service;


import co.bugu.tes.model.Property;
import co.bugu.tes.model.PropertyItem;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IPropertyService {
    int save(Property property);

    int updateById(Property property);

    int saveOrUpdate(Property property);

    int saveOrUpdate(Property property, List<PropertyItem> itemList);

    int delete(Property property);

    Property findById(Integer id);

    List<Property> findAllByObject(Property property);

    PageInfo listByObject(Property property, PageInfo<Property> pageInfo) throws Exception;

}
