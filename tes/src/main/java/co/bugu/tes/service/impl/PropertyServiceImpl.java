package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Property;
import co.bugu.tes.model.PropertyItem;
import co.bugu.tes.service.IPropertyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl extends BaseServiceImpl<Property> implements IPropertyService {

    @Override
    public void saveOrUpdate(Property property, List<PropertyItem> itemList) {

    }
}

