package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.entity.ChatSession;
import com.enterprise.aiknowledgeassistant.service.ConversationSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConversationSummaryServiceImpl
        implements ConversationSummaryService {

    @Override
    public void summarizeConversation(ChatSession session) {

        /*
         *
         * Phase 4 (RAG)
         *
         * Here we'll:
         *
         * 1. Load old conversation
         * 2. Ask LLM to summarize
         * 3. Save summary into chat_session.summary
         *
         */

        log.debug(
                "Conversation summary skipped for session {}",
                session.getId()
        );

    }

}