package com.enterprise.aiknowledgeassistant.repository;

import com.enterprise.aiknowledgeassistant.entity.ConversationEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConversationEmbeddingRepository
        extends JpaRepository<ConversationEmbedding, UUID> {

    Optional<ConversationEmbedding> findByChatMessageId(UUID chatMessageId);
}