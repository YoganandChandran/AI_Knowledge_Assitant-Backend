package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.ai.engine.PromptMetadata;

public interface PromptHistoryService {

    void record(
            PromptMetadata metadata,
            String prompt);
}