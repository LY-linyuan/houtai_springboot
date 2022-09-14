package com.tang.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author 临渊
 * @Date 2022-09-11 20:40
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;

}
