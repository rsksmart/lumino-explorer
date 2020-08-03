package org.rif.lumino.explorer;

import org.rif.lumino.explorer.boot.configuration.MongoDataBaseConfig;
import org.rif.lumino.explorer.boot.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(
    exclude = {
      MongoAutoConfiguration.class,
      MongoDataAutoConfiguration.class,
    },
    scanBasePackages = {"org.rif.lumino.explorer.controllers", "org.rif.lumino.explorer.services", "org.rif.lumino.explorer.managers", "org.rif.lumino.explorer.scheduled", "org.rif.lumino.explorer.repositories"},
    scanBasePackageClasses = {
      MongoDataBaseConfig.class,
      WebConfiguration.class,
    })
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@EnableConfigurationProperties
public class Application extends SpringBootServletInitializer {


  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
