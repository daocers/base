package co.bugu.data.service.impl;


import co.bugu.data.service.IProductService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<FactoringProduct> implements IProductService {
    @Override
    protected String getProjectName() {
        return "data";
    }
}
