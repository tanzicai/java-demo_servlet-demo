package com.tan.service;

import com.tan.dao.User;

public interface UserService {

    User login(String userCode, String userPassword);
}
