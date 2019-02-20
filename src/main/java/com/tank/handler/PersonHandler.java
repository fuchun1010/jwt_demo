package com.tank.handler;

import com.google.common.collect.Maps;
import lombok.val;

import static org.springframework.http.MediaType.*;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;

import static org.springframework.web.reactive.function.BodyInserters.*;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

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

  public Mono<ServerResponse> uploadFile(ServerRequest request) {
    return request.body(BodyExtractors.toMultipartData()).flatMap(parts -> {
      Map<String, Part> map = parts.toSingleValueMap();
      FilePart filePart = (FilePart) map.get("file");
      return ServerResponse.ok().body(BodyInserters.fromObject(filePart.filename()));
    });
  }
}
