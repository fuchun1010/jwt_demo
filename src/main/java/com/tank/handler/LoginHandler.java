package com.tank.handler;

import com.google.common.collect.Maps;
import com.tank.auth.JwtUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.MediaType.*;

import org.springframework.stereotype.Component;

import static org.springframework.web.reactive.function.BodyInserters.*;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author fuchun
 */
@Component
public class LoginHandler {

  public Mono<ServerResponse> login(ServerRequest request) {

    return request.bodyToMono(Map.class).flatMap(body -> {
      String token = this.jwtGenerator.generateToken(body);
      val response = Maps.<String, Object>newHashMap();
      response.put("token", token);
      return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(response));

    });
  }

  @Autowired
  private JwtUtils jwtGenerator;

}
