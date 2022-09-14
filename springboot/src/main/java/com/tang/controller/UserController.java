package com.tang.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.tang.dao.User;
import com.tang.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
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
    public int saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public int deleteUserById(@PathVariable(name = "id") Integer id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/deleteBatch/{ids}")
    public boolean deleteBatchByIds(@PathVariable(name = "ids") List<Integer> ids) {
        return userService.deleteBatchByIds(ids);
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

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库中查询所有数据
        List<User> userList = userService.selectFindAll();
        // 在内存操作写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出userList内的对象到excel 试用默认样式 强制输出标题
        writer.write(userList, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();

        writer.flush(out, true);
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    /**
     * excel 导入
     */
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> userList = reader.readAll(User.class);
        boolean flag = userService.saveBatch(userList);
        return flag;
    }

}

