package co.bugu.data.service.impl;


import co.bugu.data.model.Product;
import co.bugu.data.service.IProductService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService {
    @Override
    protected String getProjectName() {
        return "data";
    }
}
