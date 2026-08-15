package com.enterprise.aiknowledgeassistant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ai.conversation")
@Getter
@Setter
public class ConversationConfig {

    /**
     * Number of previous messages to send to LLM.
     */
    private int maxHistory = 10;

    /**
     * Enable conversation summary.
     */
    private boolean enableSummary = false;

}