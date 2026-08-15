package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;
import com.enterprise.aiknowledgeassistant.repository.ChatMessageRepository;
import com.enterprise.aiknowledgeassistant.service.VectorSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VectorSearchServiceImpl
        implements VectorSearchService {

    private final EmbeddingModel embeddingModel;

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatMessage> findSimilarMessages(
            UUID sessionId,
            String query,
            int topK) {

        float[] queryEmbedding =
                embeddingModel.embed(query);

        log.debug(
                "Performing semantic search. sessionId={}, topK={}",
                sessionId,
                topK
        );

        return chatMessageRepository
                .findSimilarMessages(
                        sessionId,
                        queryEmbedding,
                        topK
                );
    }
}