package com.tank.handler;

import com.google.common.collect.Maps;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
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
    String token = "hello";
    val response = Maps.newHashMap();
    response.put("token", token);
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(response));
  }
}
