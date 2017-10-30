package com.lanou.util;

import com.lanou.domain.Staff;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  过滤地址输入
 * Created by dllo on 17/10/27.
 */
@WebFilter(filterName = "Filter", urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        if (staff == null) {
            request.setAttribute("msg", "请先登录,  ^_^");
            request.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
