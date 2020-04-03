package com.example.jwt.intercept;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.jwt.JwtUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @program: com.example.jwt.intercept.xxx
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-04-02 17:16
 */
public class TokenHandlerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenHandlerInterceptor.class);

    /*
     * Controller方法调用前，返回true表示继续处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        if(requestURI.indexOf("/login") != -1 ||
            requestURI.indexOf("/error") != -1
        ){
            return true;
        }

        String token = request.getHeader("X-Access-Token");
        String msg = "token信息失效";
        if(token != null) {
            try {
                DecodedJWT decodedJWT = JwtUtils.parseHMAC256JWT(token);
                String data = decodedJWT.getClaim("data").asString();
                Gson gson = new Gson();
                HashMap hashMap = gson.fromJson(data, HashMap.class);
                Object username = hashMap.get("username");
                if (username.equals("zs")) {
                    return true;
                }
            } catch (UnsupportedEncodingException e) {
                msg = "token格式错误";
            } catch (InvalidClaimException e) {
                msg = "token信息被篡改";
            } catch (TokenExpiredException e) {
                msg = "token超时";
            }
        }

        // 此处应该使用全局异常处理器处理
        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter out = response.getWriter();){
            out.print(msg);
            out.flush();
        }
        return false;

    }
}
