package com.cts.loader;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApplication {

  public static void main(String[] args) throws SQLException {
    ApplicationContext applicationContext = SpringApplication.run(MainApplication.class, args);
    Tasks tasks = applicationContext.getBean(Tasks.class);
    tasks.read();
  }
}
