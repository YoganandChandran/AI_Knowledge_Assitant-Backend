package com.enterprise.aiknowledgeassistant.ai.engine;

import com.enterprise.aiknowledgeassistant.dto.ChatRequest;

public interface PromptEngine {

    PromptExecution build(
            ChatRequest request,
            String conversationContext);

}