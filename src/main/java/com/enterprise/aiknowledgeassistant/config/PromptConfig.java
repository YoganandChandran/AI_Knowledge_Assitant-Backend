package com.enterprise.aiknowledgeassistant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ai.prompt")
@Getter
@Setter
public class PromptConfig {

    private String version;

    private String defaultRole;

    private boolean markdownEnabled;

    private boolean includeBestPractices;

    private boolean includeSummary;

    private boolean structuredOutput;

    private boolean policyEnabled;

}
