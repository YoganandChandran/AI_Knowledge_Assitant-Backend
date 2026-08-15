package com.enterprise.aiknowledgeassistant.repository;

import com.enterprise.aiknowledgeassistant.entity.PromptHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PromptHistoryRepository
        extends JpaRepository<PromptHistory, UUID> {

    List<PromptHistory> findTop10ByUserIdOrderByCreatedAtDesc(UUID userId);

}