package co.bugu.hessian.service.impl;

import co.bugu.hessian.service.IBaseService;
import com.caucho.hessian.server.HessianServlet;

/**
 * Created by QDHL on 2017/7/7.
 */
public class LinyService extends HessianServlet implements IBaseService {
    @Override
    public String sayHello() {
        return "world ...";
    }
}
