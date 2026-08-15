package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;

import java.util.List;
import java.util.UUID;

public interface VectorSearchService {

    List<ChatMessage> findSimilarMessages(
            UUID sessionId,
            String query,
            int topK
    );
}