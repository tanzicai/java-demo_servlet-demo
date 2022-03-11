package com.tan.jdbcDemo;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 测试事务书写
 * 测试事务回滚
 * 测试事务提交
 */
public class testTransaction {
    @Test
    public void Test() throws SQLException {
        //创建来链接
        //配置信息
        String url = "jdbc:mysql://localhost:3306/servletdemo?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "crushonHS@7797";

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //创建来链接
            connection = DriverManager.getConnection(url, user, password);
            //创建事务
            connection.setAutoCommit(false);
            //内容
            String sql = "update bank set deposit = deposit - 100 where name = '张三'";
            String sql2 = "update bank set deposit = deposit + 100 where name = '李四'";
            connection.prepareStatement(sql).executeUpdate();
//            int i =1/0;
            connection.prepareStatement(sql2).executeUpdate();

            connection.commit();
            System.out.println("=============================");
            System.out.println("转账成功，正在关闭资源");
        } catch (Exception e) {
            try {
                System.out.println("=============================");
                System.out.println("转账遇到错误，开始回滚");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

}
