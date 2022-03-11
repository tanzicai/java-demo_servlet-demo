package com.tan.jdbcDemo;


import com.mysql.fabric.xmlrpc.base.Data;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Date;

/**
 * 不建议不经过服务器身份验证就建立SSL连接。
 * 根据MySQL 5.5.45+、5.6.26+和5.7.6+的要求，
 * 如果没有设置显式选项，则默认必须建立SSL连接。
 * 为了与不使用SSL的现有应用程序保持一致，verifyServerCertificate属性被设置为'false'。
 * 您需要通过设置useSSL=false来显式禁用SSL，或者设置useSSL=true并为服务器证书验证提供信任存储。
 */

public class testJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /**
         * Method1：之将诶执行语句
         * */
        //配置信息
        String url = "jdbc:mysql://localhost:3306/servletdemo?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String passeord = "crushonHS@7797";

        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //链接数据库
        Connection connection = DriverManager.getConnection(url, user, passeord);
        //发送数据
        Statement statement = connection.createStatement();
        /**
         * Method1：显式便携sql语句，后面直接执行。
         * 有安全风险，容易被xss注入
         * */
        //编写mysql语句
        String sql = "select * from user";
        //执行语句
        ResultSet resultSet = statement.executeQuery(sql);

        /**
         * Method1：显式便携sql语句，后面直接执行。
         * 有安全风险，容易被xss注入
         * */
        String sql2 = "insert into user(id,name,password,email,birthday) values (?,?,?,?,?);";
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        //写值
        preparedStatement.setInt(1, 4);
        preparedStatement.setString(2, "王二麻子");
        preparedStatement.setString(3, "123456");
        preparedStatement.setString(4, "wemz@qq.com");
        //preparedStatement.setString(5,"2010-10-11");

        preparedStatement.setString(5, new java.sql.Date(new java.util.Date().getTime()).toString());
        int i = preparedStatement.executeUpdate();
        if (i > 0) System.out.println("成功插入");
        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("password=" + resultSet.getObject("password"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birthday=" + resultSet.getObject("birthday"));
            System.out.println("=====================================================");
        }
        //关闭资源：先开后关
        preparedStatement.close();
        resultSet.close();
        statement.close();
        connection.close();
    }
}
