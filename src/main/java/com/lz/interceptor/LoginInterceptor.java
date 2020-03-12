package com.lz.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * @author will
 * @date 2020/02/24 16:12
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("name");
        if (user == null) {//未登录，返回登录页面
            request.setAttribute("msg", "您尚未登录！请先登录后再进行访问。");
//            request.getRequestDispatcher("/pages/login.html").forward(request, response);
            response.sendRedirect("/pages/login.html");
            return false;
        } else {
            //登录，放行
            return true;
        }
    }
}