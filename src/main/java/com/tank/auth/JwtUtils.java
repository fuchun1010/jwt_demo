package com.tank.auth;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Base64;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author fuchun
 */
@Component
public class JwtUtils implements Serializable {

  public String generateToken(final Map<String, Object> parameters) {
    String username = (String) parameters.get("username");
    String password = (String) parameters.get("password");

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("password", password);
    claims.put("role", "admin");

    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,
        Base64.getEncoder().encodeToString(this.secrecy.getBytes())).compact();
  }

  public Map parseToken(final String token) {
    final Claims body = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(this.secrecy.getBytes())).parseClaimsJws(token).getBody();
    final String username = body.getSubject();
    final String password = (String) body.get("password");
    final String role = (String) body.get("role");
    final Map<String, String> map = Maps.newHashMap();
    map.put("username", username);
    map.put("password", password);
    map.put("role", role);
    return map;
  }


  @Value("${jwt.secrecy}")
  private String secrecy;
}
