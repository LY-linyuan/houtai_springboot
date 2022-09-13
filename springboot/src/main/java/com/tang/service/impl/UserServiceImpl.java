package com.tang.service.impl;

import com.tang.dao.User;
import com.tang.mapper.UserMapper;
import com.tang.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 临渊
 * @Date 2022-09-11 20:46
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> selectFindAll() {
        return userMapper.selectFindAll();
    }

    @Override
    public int saveUser(User user) {
        int num = 0;
        if (user.getId() == null) {
            num = userMapper.insertUser(user);
        } else {
            num = userMapper.updateUser(user);
        }
        return num;
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public List<User> selectPage(Integer pageNum, Integer pageSize, String username) {
        return userMapper.selectPage(pageNum, pageSize, username);
    }

    @Override
    public int selectTotal(String username) {
        return userMapper.selectTotal(username);
    }

    @Override
    public boolean deleteBatchByIds(List<Integer> ids) {
        int ret = userMapper.deleteBatchByIds(ids);
        if (ret > 0) {
            return true;
        } else {
            return false;
        }
    }
}
