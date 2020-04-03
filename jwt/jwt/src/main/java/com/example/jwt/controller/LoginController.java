package com.example.jwt.controller;

import com.example.jwt.jwt.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: com.example.jwt.jwt.sa
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-04-02 10:44
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @RequestMapping(value="/login",produces = "application/json; charset=utf-8")
    public String login(String username, String password, HttpServletResponse
            response) {

        if(username.equals("zs")){
            Map<String, Object> map = new HashMap<>();
            map.put("username","zs");
            try {
                String hmac256Token = JwtUtils.getHMAC256Token(map, 0, TimeUnit.MINUTES);
                response.setHeader("X-Access-Token",hmac256Token);
                return "登陆成功";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "登陆失败";

    }

    @RequestMapping(value="/description",produces = "application/json; charset=utf-8")
    public List<Object> description() {
        List<Object> objects = new ArrayList<>();
        objects.add("resource11111111dasdsadasddddddddddddddddddddddddddddddddddddddddddddddddddd1");
        return objects;
    }
}
