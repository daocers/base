package co.bugu.spring.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by daocers on 2017/6/3.
 */
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
