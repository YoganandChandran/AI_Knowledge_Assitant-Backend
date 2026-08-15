package com.enterprise.aiknowledgeassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AiKnowledgeAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiKnowledgeAssistantApplication.class, args);
    }

}
