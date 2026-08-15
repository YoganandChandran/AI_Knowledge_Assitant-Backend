package com.enterprise.aiknowledgeassistant.repository;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {

    List<ChatMessage> findByChatSessionIdOrderByCreatedAtAsc(UUID sessionId);

    @Query(value = """
        SELECT cm.*
        FROM chat_messages cm
        JOIN conversation_embeddings ce
          ON ce.chat_message_id = cm.id
        WHERE cm.session_id = :sessionId
          AND ce.embedding IS NOT NULL
        ORDER BY ce.embedding <=> CAST(:embedding AS vector)
        LIMIT :topK
        """,
            nativeQuery = true)
    List<ChatMessage> findSimilarMessages(
            @Param("sessionId") UUID sessionId,
            @Param("embedding") float[] embedding,
            @Param("topK") int topK
    );

}