package com.example.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: com.example.jwt.controller.TestController
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-04-02 18:40
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello wangying "
;    }
}
