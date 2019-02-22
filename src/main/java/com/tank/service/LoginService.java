package com.tank.service;

import com.github.davidmoten.rx.jdbc.Database;
import com.google.common.collect.Maps;
import com.tank.auth.JwtUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Map;

/**
 * @author fuchun
 */
@Service
public class LoginService {

  public Mono<Map<String, Object>> query(final Map<String, Object> payLoad) {
    Mono<Map<String,Object>> result = Mono.defer(() -> {
      val sql = "select count(*) as cnt from tab_persons";
      val cnt = this.mysqlDataSource.select(sql).getAs(Integer.class).firstOrDefault(-1).toBlocking().first();
      Map<String, Object> map = Maps.newHashMap();

      val token = this.jwtGenerator.generateToken(payLoad);
      map.put("cnt", cnt);
      map.put("token", token);
      return Mono.just(map);
    }).subscribeOn(this.jdbcScheduler);

    return result;
  }

  @Autowired
  @Qualifier("jdbcScheduler")
  private Scheduler jdbcScheduler;

  @Autowired
  private JwtUtils jwtGenerator;

  @Autowired
  @Qualifier("asyncMySqlDb")
  private Database mysqlDataSource;
}
