package co.bugu.tes.listener;

import co.bugu.framework.core.util.ApplicationContextUtil;
import co.bugu.framework.core.util.BuguPropertiesUtil;
import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.model.Authority;
import co.bugu.tes.model.Role;
import co.bugu.tes.service.IAuthorityService;
import co.bugu.tes.service.IRoleService;
import co.bugu.tes.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/5/25.
 */
public class TesContextLoaderListener extends ContextLoaderListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        try {
            IRoleService roleService = ApplicationContextUtil.getBean(IRoleService.class);
            IAuthorityService authorityService = ApplicationContextUtil.getBean(IAuthorityService.class);

            List<Role> roleList = roleService.findByObject(null);
            Map<Integer, List<Authority>> roleIdAuthorityListMap = new HashMap<>();
            if(CollectionUtils.isNotEmpty(roleList)){
                for(Role role: roleList){
                    List<Authority> authorityList = authorityService.selectAuthorityByRole(role.getId());
                    if(CollectionUtils.isEmpty(authorityList)){
                        authorityList = new ArrayList<>();
                    }
                    roleIdAuthorityListMap.put(role.getId(), authorityList);
                }
            }
            ServletContext context = event.getServletContext();
            context.setAttribute("roleIdAuthorityListMap", roleIdAuthorityListMap);
            context.setAttribute("roleList", roleList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
