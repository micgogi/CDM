package com.example.NewsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NewsServiceApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(NewsServiceApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(NewsServiceApplication.class, args);
  }

  @Bean
  public BCryptPasswordEncoder encryptor() {
    return new BCryptPasswordEncoder();
  }
}
