package com.tan.dao;

import java.sql.Connection;

/**
 * @Description:获取用户
 * @return User
 */
public interface UserDaoImp {


    public User getLoginUser(Connection connection, String userCode) throws Exception;
}
