package org.hepx.jgt.common.token;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * User: hepanxi
 * Date: 15-1-14
 * Time: 下午1:54
 */
public class TokenTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext =(PageContext)getJspContext();
        JspWriter jw = pageContext.getOut();
        String token = TokenHelper.generateToken(pageContext.getSession());
        jw.println("<input type=\"hidden\" name=\"" + TokenHelper.INPUT_TOKEN_NAME + "\" value=\"" + token + "\" />");
    }
}
