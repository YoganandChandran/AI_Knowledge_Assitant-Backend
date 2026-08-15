package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;
import com.enterprise.aiknowledgeassistant.entity.ChatSession;

import java.util.List;

public interface ChatMessageService {

    ChatMessage saveUserMessage(ChatSession session,
                                String message);

    ChatMessage saveAssistantMessage(ChatSession session,
                                     String response);

    List<ChatMessage> getConversation(ChatSession session);

}