package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.constant.ErrorMessages;
import com.enterprise.aiknowledgeassistant.entity.ChatSession;
import com.enterprise.aiknowledgeassistant.exception.ResourceNotFoundException;
import com.enterprise.aiknowledgeassistant.repository.ChatSessionRepository;
import com.enterprise.aiknowledgeassistant.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl
        implements ConversationService {

    private final ChatSessionRepository sessionRepository;

    @Override
    public ChatSession loadSession(UUID sessionId) {

        return sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorMessages.SESSION_NOT_FOUND));

    }

    @Override
    public void updateLastMessage(ChatSession session) {

        session.setLastMessageAt(LocalDateTime.now());

        sessionRepository.save(session);

    }

}