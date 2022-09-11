package com.tang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 临渊
 * @Date 2022-09-11 13:34
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello springboot + vue";
    }

}
