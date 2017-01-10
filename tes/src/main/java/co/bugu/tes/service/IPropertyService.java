package co.bugu.tes.service;


import co.bugu.framework.core.service.IBaseService;
import co.bugu.tes.model.Property;
import co.bugu.tes.model.PropertyItem;

import java.util.List;

public interface IPropertyService extends IBaseService<Property> {

    void saveOrUpdate(Property property, List<PropertyItem> itemList);
}
