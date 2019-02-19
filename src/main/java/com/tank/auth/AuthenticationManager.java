package com.tank.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fuchun
 */
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

  @Override
  public Mono<Authentication> authenticate(final Authentication authentication) {
    final String token = authentication.getCredentials().toString();
    final Map<String, Object> body = jwtUtils.parseToken(token);
    final String username = (String) body.get("username");

    List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
    List<GrantedAuthority> grantedAuthorities = roles.stream().map(name -> new SimpleGrantedAuthority(name)).collect(Collectors.toList());
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(User.withUsername(username), null, grantedAuthorities);
    //TODO check username is exists in db
    return Mono.just(auth);
  }

  @Autowired
  private JwtUtils jwtUtils;
}


