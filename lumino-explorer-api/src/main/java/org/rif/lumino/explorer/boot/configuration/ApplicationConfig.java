package org.rif.lumino.explorer.boot.configuration;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.rif.lumino.explorer.repositories")
public class ApplicationConfig {

  @Autowired private MongoClient mongoClient;

  @Bean
  public MongoClient mongoClient() {
    return new MongoClient("localhost");
  }

  public @Bean MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongoClient, "lumino_explorer");
  }
}
