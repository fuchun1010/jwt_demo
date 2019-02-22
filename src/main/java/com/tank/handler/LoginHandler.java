package com.tank.handler;

import com.tank.service.LoginService;
import com.tank.tool.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author fuchun
 */
@Component
@Slf4j
public class LoginHandler {

  public Mono<ServerResponse> login(ServerRequest request) {
    return request.bodyToMono(Map.class)
        .flatMap(this.loginService::query)
        .flatMap(JsonUtils::convert2);
  }


  @Autowired
  private LoginService loginService;

}
