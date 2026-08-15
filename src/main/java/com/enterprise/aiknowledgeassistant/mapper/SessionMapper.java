package com.enterprise.aiknowledgeassistant.mapper;

import com.enterprise.aiknowledgeassistant.dto.session.CreateSessionResponse;
import com.enterprise.aiknowledgeassistant.dto.session.SessionResponse;
import com.enterprise.aiknowledgeassistant.entity.ChatSession;

public final class SessionMapper {

    private SessionMapper() {
    }

    public static SessionResponse toResponse(ChatSession entity) {

        return SessionResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .status(entity.getStatus().name())
                .summary(entity.getSummary())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .lastMessageAt(entity.getLastMessageAt())
                .build();
    }

    public static CreateSessionResponse toCreateResponse(ChatSession entity) {

        return CreateSessionResponse.builder()
                .sessionId(entity.getId())
                .message("Session created successfully")
                .build();
    }

}