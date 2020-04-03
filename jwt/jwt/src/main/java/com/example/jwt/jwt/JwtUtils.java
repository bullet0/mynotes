package com.example.jwt.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: com.example.jwt.jwt.sxx
 * @description:
 * JWT由三部分组成
 *    header: 声明类型和加密的算法 {'typ': 'JWT','alg': 'HS256'}
 *    payload: 一些附属信息,超时时间... 组成另一个json串,内容可以自己设置
 *    signature: 签名,将前两部分先拼成字符串,然后用加密算法加密
 * 所以最后得到的token为 header(base64编码).payload(base64编码).signature(加密签名)
 * 前两部分可以随便解开,所以不要在payload中携带私密信息
 *
 * @author: Mr.BULLET
 * @create: 2020-04-02 10:43
 */
public class JwtUtils {
    private final static String HMAC256_SECRET = "bd8b0c81f2ce48fc8d19adb29b531551";
    private final static String ISSUER = "Mr.BULLET";
    private final static String SUBJECT = "none";
    private final static String AUDIENCE = "anyone";
    private static String PUBLIC_KEY;
    private static String PRIVATE_KEY;
    /**
     * @author Mr.BULLET
     * 获取token
     * @param data 要放入token中的数据
     * @param delayTime 存活时间
     * @param timeUnit 存活时间单位
     */
    public static String getHMAC256Token(Object data, long delayTime, TimeUnit timeUnit) throws UnsupportedEncodingException {
        long convert = TimeUnit.MILLISECONDS.convert(delayTime, timeUnit);
        Algorithm algorithm = Algorithm.HMAC256(HMAC256_SECRET);
        String token = JWT.create()
                .withIssuer(ISSUER)   //发布者
                .withSubject(SUBJECT)    //主题
                .withAudience(AUDIENCE)     //观众，相当于接受者
                .withIssuedAt(new Date())   // 生成签名的时间
                .withExpiresAt(new Date(new Date().getTime() + convert))    // 5分钟后过期
                .withClaim("data", new Gson().toJson(data)) //自定义字段存数据
                .withNotBefore(new Date())  //生效时间
                .withJWTId(UUID.randomUUID().toString())    //编号
                .sign(algorithm);
        return token;
    }
    /**
     * @author Mr.BULLET
     * 获取token
     * @param data 要放入token中的数据
     * @param delayTime 存活时间
     * @param timeUnit 存活时间单位
     */
    public static String getRSAToken(Object data, long delayTime, TimeUnit timeUnit , Map<String,Object> keyMap) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        long convert = TimeUnit.MILLISECONDS.convert(delayTime, timeUnit);
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey)keyMap.get("RSAPublicKey"),
                (RSAPrivateKey)keyMap.get("RSAPrivateKey"));
        String token = JWT.create()
                .withIssuer(ISSUER)   //发布者
                .withSubject(SUBJECT)    //主题
                .withAudience(AUDIENCE)     //观众，相当于接受者
                .withIssuedAt(new Date())   // 生成签名的时间
                .withExpiresAt(new Date(new Date().getTime() + convert))    // 5分钟后过期
                .withClaim("data", new Gson().toJson(data)) //自定义字段存数据
                .withNotBefore(new Date())  //生效时间
                .withJWTId(UUID.randomUUID().toString())    //编号
                .sign(algorithm);
        return token;
    }
    /**
     * @author Mr.BULLET
     * 验证token
     * @param token token
     * @throws InvalidClaimException 摘要异常,需要调用者手动处理,比如发布者不一致..会抛出此异常
     * @throws TokenExpiredException token过期,需要调用者手动处理
     */
    public static DecodedJWT parseHMAC256JWT(String token) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(HMAC256_SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }
    /**
     * @author Mr.BULLET
     * 验证token
     * @param token token
     * @throws InvalidClaimException 摘要异常,需要调用者手动处理,比如发布者不一致..会抛出此异常
     * @throws TokenExpiredException token过期,需要调用者手动处理
     */
    public static DecodedJWT parseRASJWT(String token,Map<String,Object> keyMap) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey)keyMap.get("RSAPublicKey"),
                (RSAPrivateKey)keyMap.get("RSAPrivateKey"));
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        testHMAC256Token();
//        testRASToken();
    }

    public static void testHMAC256Token() throws UnsupportedEncodingException, NoSuchAlgorithmException{
        // 生成token
        Map<String, Object> map = new HashMap<>();
        map.put("username","admin");
        String token = JwtUtils.getHMAC256Token(map,10,TimeUnit.MINUTES);
        System.out.println(token);
        // 解析token
        DecodedJWT decodedJWT = parseHMAC256JWT(token);
        System.out.println();
        System.out.println(decodedJWT.getClaims().containsKey("data"));
        System.out.println(decodedJWT.getClaims().get("data").asString());
        Gson gson = new Gson();
        HashMap data = gson.fromJson(decodedJWT.getClaims().get("data").asString(), HashMap.class);
        System.out.println(data.get("username"));
        System.out.println("=====================================");
        Date expiresAt11 = decodedJWT.getIssuedAt();
        System.out.println(expiresAt11);
        Date expiresAt = decodedJWT.getExpiresAt();
        System.out.println(expiresAt);
        System.out.println(decodedJWT.getAlgorithm());
    }
    public static void testRASToken() throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Map<String, Object> keyMap = RSAUtils.initKey();
        // 生成token
        Map<String, Object> map = new HashMap<>();
        map.put("username","hello");
        String token = JwtUtils.getRSAToken(map,1,TimeUnit.MINUTES,keyMap);
        System.out.println(token);
        // 解析token
        DecodedJWT decodedJWT = parseRASJWT(token,keyMap);
        System.out.println();
        System.out.println(decodedJWT.getClaims().containsKey("data"));
        System.out.println(decodedJWT.getClaims().get("data").asString());
        Gson gson = new Gson();
        HashMap data = gson.fromJson(decodedJWT.getClaims().get("data").asString(), HashMap.class);
        System.out.println(data.get("username"));
        System.out.println("=====================================");
        Date expiresAt11 = decodedJWT.getIssuedAt();
        System.out.println(expiresAt11);
        Date expiresAt = decodedJWT.getExpiresAt();
        System.out.println(expiresAt);
        System.out.println(decodedJWT.getAlgorithm());
    }
}
