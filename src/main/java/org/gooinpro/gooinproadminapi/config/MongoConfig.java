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
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");  // MongoDB 연결 URL
        return new MongoTemplate(mongoClient, "gooinprochatdb");  // 데이터베이스 이름 지정
    }
}
