package co.bugu.tes.tag;

import co.bugu.framework.core.util.ApplicationContextUtil;
import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.service.IUserService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by user on 2017/5/13.
 */
public class HasPromission extends SimpleTagSupport {
    IUserService userService = ApplicationContextUtil.getBean(IUserService.class);
    private String name;
    public void setName(String name){
        this.name = name;
    }

    StringWriter writer = new StringWriter();
    @Override
    public void doTag() throws JspException, IOException {
        String[] authorities = name.split(",");
        PageContext pageContext = (PageContext) getJspContext();

        ServletRequest request = pageContext.getRequest();
        Integer userId = BuguWebUtil.getUserId((HttpServletRequest) request);

        boolean res = userService.hasAuthority(userId, authorities);
        if(res){
            getJspBody().invoke(writer);
            getJspContext().getOut().println(writer.toString());
        }
        super.doTag();
    }
}
