package co.bugu.data.service.impl;

import co.bugu.data.model.PushParty;
import co.bugu.data.service.IPushPartyService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
* .
*/
@Service
public class PushPartyServiceImpl extends BaseServiceImpl<PushParty> implements IPushPartyService {
    @Override
    protected String getProjectName() {
        return "data";
    }

}
