package com.example.mongotest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @program: com.example.mongotest.config.Conf
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-20 11:09
 */
@Configuration
public class Conf {
    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory factory){
        return new MongoTransactionManager(factory);
    }
}
