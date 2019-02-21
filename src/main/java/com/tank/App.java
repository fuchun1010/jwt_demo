package com.tank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author fuchun
 * @date 2019-02-19
 */
@SpringBootApplication
@Slf4j
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class);
  }

  @Bean
  public CommandLineRunner initCommandRunner() {

    return (args) -> {
      log.info(this.name + " start success");
    };
  }

  @Value("${server.name}")
  private String name;
}
