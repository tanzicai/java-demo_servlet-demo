package com.tan.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

// 更行了jar包，从以前的jvax，更新成了jakarta
//public class HelloServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//        System.out.println("doGet");
//        PrintWriter write = resp.getWriter();
//        write.println("Hello,This is Servlet");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//}
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //super.doGet(req, resp);在重写的httpGet中不应该继承super()否则会出现405
        System.out.println("Run successfully");
        System.out.println("中文编码测试");

        PrintWriter write = resp.getWriter();
        write.println("Hello,Servlet run successfully");
        write.println("中文字符测试");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    }
}