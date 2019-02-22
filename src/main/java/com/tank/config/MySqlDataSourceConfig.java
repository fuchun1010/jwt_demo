package com.tank.config;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
import com.github.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

/**
 * @author tank198435163.com
 * @date 2019-02-20
 */
@Component
public class MySqlDataSourceConfig {

  @Bean("asyncMySqlDb")
  public Database initDataBase() {
    ConnectionProvider connectionProvider = new ConnectionProviderFromUrl(this.url, this.user, this.password);
    return Database.from(connectionProvider).asynchronous();
  }

  @Value("${mysql.url}")
  private String url;

  @Value("${mysql.user}")
  private String user;

  @Value("${mysql.password}")
  private String password;

  @Bean
  public Scheduler jdbcScheduler() {
    return Schedulers.fromExecutor(Executors.newFixedThreadPool(12));
  }

}
