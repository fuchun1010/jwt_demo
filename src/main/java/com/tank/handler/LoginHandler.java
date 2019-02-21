package com.tank.handler;

import com.github.davidmoten.rx.jdbc.Database;
import com.google.common.collect.Maps;
import com.tank.auth.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author fuchun
 */
@Component
@Slf4j
public class LoginHandler {

  public Mono<ServerResponse> login(ServerRequest request) {

    return request.bodyToMono(Map.class).flatMap(body -> {
      String token = this.jwtGenerator.generateToken(body);
      val response = Maps.<String, Object>newHashMap();
      response.put("token", token);
      val sql = "select count(*) as cnt from tab_persons";
      val cnt = this.mysqlDataSource.select(sql).getAs(Integer.class).firstOrDefault(-1).toBlocking().first();
      response.put("cnt", cnt);
      return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(response));

    });
  }

  @Autowired
  private JwtUtils jwtGenerator;

  @Autowired
  @Qualifier("asyncMySqlDb")
  private Database mysqlDataSource;

}
