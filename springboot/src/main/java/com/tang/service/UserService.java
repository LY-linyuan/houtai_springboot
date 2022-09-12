package com.tang.service;

import com.tang.dao.User;

import java.util.List;

/**
* @Author  临渊
* @Date    2022-09-11 20:44
*/
public interface UserService {

    List<User> selectFindAll();

    int saveUser(User user);

    int deleteUserById(int id);

    List<User> selectPage(Integer pageNum, Integer pageSize, String username);

    int selectTotal(String username);
}
