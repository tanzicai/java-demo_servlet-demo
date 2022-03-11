package com.tan.until;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description:操作数据库的公共类
 */
public class BaseDao {

    //初始化数据库连接参数
    private static String url;
    private static String driver;
    private static String username;
    private static String password;

    /* TODO:初始化参数 */
    static {
        Properties properties = new Properties();
        try {
            InputStream i = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(i);
            //初始化参数
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* TODO:获取数据库链接 */
    public static Connection getCOnnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /* TODO:查询公共类 */
    public static ResultSet excute(
            Connection connection,
            PreparedStatement preparedStatement,
            ResultSet resultSet,
            String sql,
            Object[] params
    ) {
        //执行预编译的sql
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //数据库更新类
    public static Integer excute(
            Connection connection,
            PreparedStatement preparedStatement,
            String sql,
            Object[] params
    ) {
        //执行预编译的sql
        int executeUpdate = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            executeUpdate = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executeUpdate;
    }

    // 释放资源
    public static Boolean closeRepository(
            Connection connection,
            PreparedStatement preparedStatement,
            ResultSet resultSet
    ) {
        //初始化状态变量
        boolean flag = true;
        // 一次释放资源
        if (resultSet != null) {
            try {
                resultSet.close();
                //GC回收：对象设置成空引用利于JVM垃圾回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                //GC回收：对象设置成空引用利于JVM垃圾回收
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = flag;
            }
        }

        if (connection != null) {
            try {
                connection.close();
                //GC回收：对象设置成空引用利于JVM垃圾回收
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

}
