package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.dto.ChatRequest;
import com.enterprise.aiknowledgeassistant.dto.ChatResponse;

public interface ChatOrchestratorService {

    ChatResponse chat(ChatRequest request);

}