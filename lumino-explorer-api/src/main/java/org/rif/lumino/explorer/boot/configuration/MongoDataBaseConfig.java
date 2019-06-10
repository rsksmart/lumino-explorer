package org.rif.lumino.explorer.boot.configuration;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.rif.lumino.explorer.repositories")
public class MongoDataBaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(MongoDataBaseConfig.class);

    @Value("${lumino.explorer.api.mongo.databasename}")
    private String databaseName;

    @Value("${lumino.explorer.api.mongo.databasehost}")
    private String databaseHost;

    @Value("${lumino.explorer.api.profile}")
    private String profile;

    @Autowired
    private MongoClient mongoClient;

    @Bean
    public MongoClient mongoClient() {
        logger.info("Mongo is running with " + profile + " properties");
        return new MongoClient(databaseHost);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient, databaseName);
    }
}
