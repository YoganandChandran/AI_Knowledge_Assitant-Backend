package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;

public interface EmbeddingService {

    void generateAndStoreEmbedding(ChatMessage message);

}
