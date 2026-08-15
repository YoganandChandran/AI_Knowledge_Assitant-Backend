package com.enterprise.aiknowledgeassistant.ai.engine;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromptExecution {

    private final String prompt;

    private final PromptMetadata metadata;
}