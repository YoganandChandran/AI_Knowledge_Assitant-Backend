package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.entity.ChatSession;

public interface ConversationSummaryService {

    void summarizeConversation(ChatSession session);

}