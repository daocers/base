package co.bugu.data.service.impl;


import co.bugu.data.model.FactoringProductRelation;
import co.bugu.data.service.IFactoringProductRelationService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FactoringProductRelationServiceImpl extends BaseServiceImpl<FactoringProductRelation> implements IFactoringProductRelationService {
    @Override
    protected String getProjectName() {
        return "data";
    }
}
