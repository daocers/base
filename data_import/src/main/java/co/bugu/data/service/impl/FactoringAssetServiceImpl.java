package co.bugu.data.service.impl;


import co.bugu.data.model.FactoringAsset;
import co.bugu.data.service.IFactoringAssetService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FactoringAssetServiceImpl extends BaseServiceImpl<FactoringAsset> implements IFactoringAssetService {
    @Override
    protected String getProjectName() {
        return "data";
    }
}
