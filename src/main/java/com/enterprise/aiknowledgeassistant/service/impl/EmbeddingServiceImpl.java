package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;
import com.enterprise.aiknowledgeassistant.entity.ConversationEmbedding;
import com.enterprise.aiknowledgeassistant.repository.ConversationEmbeddingRepository;
import com.enterprise.aiknowledgeassistant.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmbeddingServiceImpl implements EmbeddingService {

    private final EmbeddingModel embeddingModel;

    private final ConversationEmbeddingRepository
            conversationEmbeddingRepository;

    @Override
    @Transactional
    public void generateAndStoreEmbedding(ChatMessage message) {

        if (message == null || message.getContent() == null
                || message.getContent().isBlank()) {

            log.warn("Skipping embedding generation because message is empty");
            return;
        }

        log.debug(
                "Generating embedding for message {}",
                message.getId()
        );

        float[] vector =
                embeddingModel.embed(message.getContent());

        ConversationEmbedding embedding =
                conversationEmbeddingRepository
                        .findByChatMessageId(message.getId())
                        .orElseGet(ConversationEmbedding::new);

        embedding.setChatMessage(message);

        embedding.setEmbeddingModel(
                embeddingModel.getClass().getSimpleName()
        );

        embedding.setEmbedding(vector);

        conversationEmbeddingRepository.save(embedding);

        log.info(
                "Embedding stored successfully. messageId={}, dimensions={}",
                message.getId(),
                vector.length
        );
    }
}
