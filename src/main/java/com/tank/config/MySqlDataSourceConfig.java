package com.tank.config;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
import com.github.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author tank198435163.com
 * @date 2019-02-20
 */
@Component
public class MySqlDataSourceConfig {

  @Bean("asyncMySqlDb")
  public Database initDataBase() {
    ConnectionProvider connectionProvider = new ConnectionProviderFromUrl(this.url, this.user, this.password);
    return Database.from(connectionProvider);
  }


  @Value("${mysql.url}")
  private String url;

  @Value("${mysql.user}")
  private String user;

  @Value("${mysql.password}")
  private String password;

  @Value("${mysql.maxIdle}")
  private int maxIdle;

  @Value("${mysql.idleTimeBeforeHealthCheck}")
  private int idleTimeBeforeHealthCheck;

  @Value("${mysql.connectionRetryInterval}")
  private int connectionRetryInterval;
}
