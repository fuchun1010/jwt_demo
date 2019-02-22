package com.tank.tool;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class JsonUtils {

  public static  Mono<ServerResponse> convert2(final Object rs) {
    return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(rs));
  }
}
