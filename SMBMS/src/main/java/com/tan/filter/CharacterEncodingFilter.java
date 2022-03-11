package com.tan.filter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * @Description:字符过滤
 */
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //设置返回格式
        response.setContentType("text/html;charset=UTF-8");
        //过滤器链
        chain.doFilter(request, response);
    }
}
