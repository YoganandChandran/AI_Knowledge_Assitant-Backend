package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.dto.ChatRequest;
import com.enterprise.aiknowledgeassistant.dto.ChatResponse;

public interface ChatService {

    public ChatResponse chat(ChatRequest request, String conversationContext);

}
