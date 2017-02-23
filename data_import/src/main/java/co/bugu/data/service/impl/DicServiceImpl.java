package co.bugu.data.service.impl;

import co.bugu.data.model.Dic;
import co.bugu.data.service.IDicService;
import co.bugu.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/2/23.
 */
@Service
public class DicServiceImpl extends BaseServiceImpl<Dic> implements IDicService {
    @Override
    protected String getProjectName() {
        return "data";
    }
}
