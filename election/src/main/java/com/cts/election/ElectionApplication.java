package com.cts.election;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ElectionApplication extends SpringBootServletInitializer {
  @Autowired private Reader reader;

  public static void main(String[] args) {

    ApplicationContext applicationContext = SpringApplication.run(ElectionApplication.class, args);
    //		Reader reader=applicationContext.getBean(Reader.class);
    //		reader.read();

  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
    reader.read();
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    // TODO Auto-generated method stub
    return builder.sources(ElectionApplication.class);
  }
}
