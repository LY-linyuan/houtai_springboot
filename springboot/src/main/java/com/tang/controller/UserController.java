package com.tang.controller;

import com.tang.dao.User;
import com.tang.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 临渊
 * @Date 2022-09-11 21:09
 */

@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/selectFindAllUser")
    public List<User> selectFindAllUser() {
        return userService.selectFindAll();
    }

    @PostMapping("/saveUser")
    public int saveUser(User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/deleteUserById")
    public int deleteUserById(int id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/page")
    public Map<String, Object> findPage(Integer pageNum, Integer pageSize, String username) {
        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userService.selectPage(pageNum, pageSize, username);
        int count = userService.selectTotal(username);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", count);
        map.put("data", data);
        return map;
    }

}