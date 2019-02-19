package com.tank.handler;

import com.google.common.collect.Maps;
import lombok.val;

import static org.springframework.http.MediaType.*;

import org.springframework.stereotype.Component;

import static org.springframework.web.reactive.function.BodyInserters.*;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.LinkedList;

@Component
public class PersonHandler {

  public Mono<ServerResponse> queryAllPerson(ServerRequest request) {
    val list = new LinkedList<String>() {{
      add("lisi");
      add("wangwu");
    }};

    val response = Maps.<String, Object>newHashMap();
    response.put("result", list);
    return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(response));
  }
}
