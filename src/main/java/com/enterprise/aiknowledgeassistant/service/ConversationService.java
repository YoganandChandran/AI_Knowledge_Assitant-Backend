package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.entity.ChatSession;

import java.util.UUID;

public interface ConversationService {

    ChatSession loadSession(UUID sessionId);

    void updateLastMessage(ChatSession session);

}