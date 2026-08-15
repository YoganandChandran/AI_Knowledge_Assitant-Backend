package com.enterprise.aiknowledgeassistant.repository;

import com.enterprise.aiknowledgeassistant.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatSessionRepository extends JpaRepository<ChatSession, UUID> {

    List<ChatSession> findByUserId(UUID userId);

    List<ChatSession> findByGuestSessionId(String guestSessionId);

}