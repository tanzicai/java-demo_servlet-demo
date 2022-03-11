package com.tan.dao;

import com.tan.until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao implements UserDaoImp {
    @Override
    public User getLoginUser(Connection connection, String userCode) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        if (userCode != null) {
            String sql = "select * from smbms_user where userCode=?";
            String[] params = {userCode};

            //解析
            resultSet = BaseDao.excute(connection, preparedStatement, resultSet, sql, params);

            if (resultSet.next()) {
                user = new User();
                //设置属性
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setUserName(resultSet.getString("userName"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreateionBy(resultSet.getInt("createdBy"));
                user.setCreatetionData(resultSet.getDate("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyData(resultSet.getDate("modifyDate"));
            }

            //GC回收
            BaseDao.closeRepository(connection, preparedStatement, resultSet);
        }

        return user;
    }


}
