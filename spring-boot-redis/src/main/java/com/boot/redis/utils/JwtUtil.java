package com.boot.redis.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoxiaoping
 * @Description: jwt 工具类
 * @Date 2020/3/6
 **/
@Slf4j
public class JwtUtil {

  /**
   * 过期时间 一小时
   */
  private static final long EXPIRE_TIME = 60;

  /**
   * token 私钥
   */
  public static final String TOKEN_SECRET = "asdfasff";

  /**
   * 生成签名
   *
   * @param userName
   * @return
   */
  public static String sign(String userName) {
    LocalDateTime dateTime = LocalDateTime.now().plusMinutes(EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
    //设置头信息
    HashMap<String, Object> header = new HashMap<>(2);
    header.put("typ", "JWT");
    header.put("alg", "HS256");
    String token = JWT.create()
        .withHeader(header)
        .withClaim("loginName", userName)
//        .withClaim("userId", userId)
        .withExpiresAt(Date.from(dateTime.atZone(ZoneOffset.systemDefault()).toInstant()))
        .withIssuedAt(new Date())
        .sign(algorithm);
    return token;
  }

  public static Map<String, Claim> verifyToken(String token) {
    try {
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
      DecodedJWT jwt = verifier.verify(token);
      if(jwt.getExpiresAt().before(new Date())){
        log.error("token 过期");
        return null;
      }
      return verifier.verify(token).getClaims();
    } catch (Exception e) {
      log.error("token 解析异常");
      return null;
    }
  }
}
