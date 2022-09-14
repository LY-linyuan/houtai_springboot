package com.tang.mapper;

import com.tang.dao.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 临渊
 * @Date 2022-09-11 20:42
 */

@Mapper
public interface UserMapper {

    List<User> selectFindAll();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUserById(int id);

    List<User> selectPage(Integer pageNum, Integer pageSize, String username);

    int selectTotal(String username);

    int deleteBatchByIds(List<Integer> ids);

    int saveBatch(List<User> userList);
}
