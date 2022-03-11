package com.tan.servlet;


import jakarta.servlet.*;

import java.io.IOException;

/**
 * 注意:
 * tomcat在10中舍弃了javax中的API
 * 使用了jakarta相关的代码
 * 包括jsp、和servlet-api的包均作出了改变
 * maven引用也需要做出改变
 */

/**
 * 注意：
 * 执行顺序：
 * init：tomcat服务启动时就开始执行，等会服务请求；
 * doFilter：在任何符合过滤器请求的时候都会执行，记住必须让chain链式同行
 * destroy：在tomcat服务销毁时，代码快会执行
 */

public class Filterdemo implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //更改请求及返回编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //页面编码
        response.setContentType("text/html;charset=UTF-8");


        /**
         * 注意调用chain链不是直接调用函数，而是调用chain的方法
         * 错误使用：doFilter(request,response,chain);
         * 正确使用：chain.doFilter(request,response);
         * */
        //chain链
        chain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}