package com.tan.service;

import com.tan.dao.User;
import com.tan.dao.UserDao;
import com.tan.until.BaseDao;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class UserServiceImp implements UserService {
    private UserDao userDao;


    public UserServiceImp() {
        this.userDao = new UserDao();
    }

    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;
        //获取User
        connection = BaseDao.getCOnnection();
        try {
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeRepository(connection, null, null);
        }
        //密码匹配
        if (user != null) {
            if (!user.getUserPassword().equals(userPassword)) {
                return null;
            }
        }
        return user;
    }


    @Test
    public void test() {
        UserServiceImp userService = new UserServiceImp();
        User admin = userService.login("admin", "1234567");
        System.out.println(
                "======================================" + "\n" +
                        "\t" + "ID:" + "\t" + admin.getId() + "\t" + "\n" +
                        "\t" + "userCode:" + "\t" + admin.getUserCode() + "\t" + "\n" +
                        "\t" + "userName:" + "\t" + admin.getUserName() + "\t" + "\n" +
                        "\t" + "password:" + "\t" + admin.getUserPassword() + "\t" + "\n" +
                        "\t" + "gender:" + "\t" + admin.getGender() + "\t" + "\n" +
                        "\t" + "birthday:" + "\t" + admin.getBirthday() + "\t" + "\n" +
                        "\t" + "phone:" + "\t" + admin.getPhone() + "\t" + "\n" +
                        "\t" + "address:" + "\t" + admin.getAddress() + "\t" + "\n" +
                        "\t" + "userRole:" + "\t" + admin.getUserRole() + "\t" + "\n" +
                        "\t" + "createdBy:" + "\t" + admin.getCreateionBy() + "\t" + "\n" +
                        "\t" + "creationData:" + "\t" + admin.getCreatetionData() + "\t" + "\n" +
                        "\t" + "modifyBy:" + "\t" + admin.getModifyBy() + "\t" + "\n" +
                        "\t" + "modifyData:" + "\t" + admin.getModifyData() + "\t" + "\n" +
                        "======================================"
        );
    }
}
