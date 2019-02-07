package cn.jboa.filter;

import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object emp = session.getAttribute(Constants.AUTH_EMPLOYEE);
        if (emp==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
