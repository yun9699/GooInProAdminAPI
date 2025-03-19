package org.gooinpro.gooinproadminapi.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        // 인증 정보를 포함한 MongoDB 연결 URI
        String mongoUri = "mongodb://gooinprochatdbuser:gooinprochatdbuser@localhost:27017/gooinprochatdb?authSource=gooinprochatdb";

        MongoClient mongoClient = MongoClients.create(mongoUri);
        return new MongoTemplate(mongoClient, "gooinprochatdb");
    }
}