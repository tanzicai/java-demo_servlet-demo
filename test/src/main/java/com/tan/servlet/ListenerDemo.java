package com.tan.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class ListenerDemo implements HttpSessionListener {

    /**
     * 创建Session监听
     * 创建一次session就会执行一次事件
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer onlineNum = (Integer) context.getAttribute("OnlineNum");
        if (onlineNum == null) onlineNum = new Integer(1);
        else onlineNum++;
        context.setAttribute("OnlineNum", onlineNum);

    }


    /**
     * 销毁Session监听
     * 销毁一次session就会执行一次事件
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer onlineNum = (Integer) context.getAttribute("OnlineNum");
        if (onlineNum == null) onlineNum = new Integer(0);
        else onlineNum--;
        context.setAttribute("OnlineNum", onlineNum);
    }
}
