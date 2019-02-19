package com.tank.router;

import com.tank.handler.LoginHandler;
import com.tank.handler.PersonHandler;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author fuchun
 * @date 2019-02-19
 */
@Component
@EnableWebFlux
public class RouterConfiguration {

  /**
   * @return
   */
  @Bean
  public RouterFunction<ServerResponse> registerRouter() {
    val loginRouter = POST("/login").and(JSON_FORMATTER);
    val personRouter = GET("/v1/persons").and(JSON_FORMATTER);

    return route(loginRouter, this.loginHandler::login)
        .andRoute(personRouter,this.personHandler::queryAllPerson);

  }

  @Autowired
  private LoginHandler loginHandler;

  @Autowired
  private PersonHandler personHandler;

  private final RequestPredicate JSON_FORMATTER = accept(APPLICATION_JSON);
}
