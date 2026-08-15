package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.ai.context.PromptContextBuilder;
import com.enterprise.aiknowledgeassistant.dto.ChatRequest;
import com.enterprise.aiknowledgeassistant.dto.ChatResponse;
import com.enterprise.aiknowledgeassistant.entity.ChatMessage;
import com.enterprise.aiknowledgeassistant.entity.ChatSession;
import com.enterprise.aiknowledgeassistant.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatOrchestratorServiceImpl
        implements ChatOrchestratorService {

    private final ChatService chatService;

    private final ConversationService conversationService;

    private final ChatMessageService chatMessageService;

    private final PromptContextBuilder promptContextBuilder;

    private final ConversationSummaryService conversationSummaryService;

    private final VectorSearchService vectorSearchService;

    @Override
    public ChatResponse chat(ChatRequest request) {

        ChatSession session =
                conversationService.loadSession(
                        request.getSessionId());

        List<ChatMessage> history =
                chatMessageService.getConversation(session);

        List<ChatMessage> relevantMessages =
                vectorSearchService.findSimilarMessages(
                        session.getId(),
                        request.getMessage(),
                        5
                );

        String conversationContext =
                promptContextBuilder.buildConversationContext(
                        history,
                        relevantMessages
                );

        chatMessageService.saveUserMessage(
                session,
                request.getMessage()
        );

        ChatResponse aiResponse =
                chatService.chat(
                        request,
                        conversationContext);

        ChatMessage assistantMessage =
                chatMessageService.saveAssistantMessage(
                        session,
                        aiResponse.getResponse());

        conversationService.updateLastMessage(session);

        conversationSummaryService.summarizeConversation(session);

        aiResponse.setSessionId(session.getId());

        aiResponse.setMessageId(
                assistantMessage.getId());

        return aiResponse;

    }

}