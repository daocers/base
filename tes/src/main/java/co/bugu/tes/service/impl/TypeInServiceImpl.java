package co.bugu.tes.service.impl;

import co.bugu.tes.model.TypeIn;
import co.bugu.tes.service.ITypeInService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
/**
* .
*/
@Service
public class TypeInServiceImpl extends BaseServiceImpl<TypeIn> implements ITypeInService {
    @Override
    protected String getProjectName() {
        return "tes";
    }

}
