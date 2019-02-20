package com.tank.router;

import com.tank.handler.LoginHandler;
import com.tank.handler.PersonHandler;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author fuchun
 * @date 2019-02-19
 */
@Configuration
public class RouterConfiguration {

  /**
   * @return
   */
  @Bean
  public RouterFunction<ServerResponse> registerRouter() {
    val loginRouter = POST("/login").and(JSON_FORMATTER);
    val queryAllPersonRouter = GET("/v1/persons").and(JSON_FORMATTER);
    val uploadFileRouter = POST("/v1/upload").and(FILE_FORMATTER);

    return route(loginRouter, this.loginHandler::login)
        .andRoute(queryAllPersonRouter, this.personHandler::queryAllPerson)
        .andRoute(uploadFileRouter, this.personHandler::uploadFile);

  }

  @Autowired
  private LoginHandler loginHandler;

  @Autowired
  private PersonHandler personHandler;

  private final RequestPredicate JSON_FORMATTER = accept(APPLICATION_JSON);
  private final RequestPredicate FILE_FORMATTER = accept(MULTIPART_FORM_DATA);
}

