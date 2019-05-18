package com.bookStore.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.bookStore.commons.beans.User;
import java.io.IOException;

public class LoginTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        PageContext context=(PageContext)this.getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        User login_user= (User) context.getSession().getAttribute("login_user");
        if(login_user==null){
            response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
        }
    }
}
