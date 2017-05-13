package co.bugu.tes.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by user on 2017/5/13.
 */
public class HasPromission extends SimpleTagSupport {
    private String name;
    public void setName(String name){
        this.name = name;
    }

    StringWriter writer = new StringWriter();
    @Override
    public void doTag() throws JspException, IOException {
        if(name != null){
            JspWriter out = getJspContext().getOut();
            out.println(name);
        }else{
            getJspBody().invoke(writer);
            getJspContext().getOut().println(writer.toString());
        }
    }
}
