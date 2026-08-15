package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.ai.client.AIClient;
import com.enterprise.aiknowledgeassistant.ai.engine.PromptEngine;
import com.enterprise.aiknowledgeassistant.ai.engine.PromptExecution;
import com.enterprise.aiknowledgeassistant.dto.ChatRequest;
import com.enterprise.aiknowledgeassistant.dto.ChatResponse;
import com.enterprise.aiknowledgeassistant.service.ChatService;
import com.enterprise.aiknowledgeassistant.service.PromptHistoryService;
import com.enterprise.aiknowledgeassistant.utils.AIExecutionTimer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final AIClient aiClient;

    private final PromptEngine promptEngine;

    private final PromptHistoryService promptHistoryService;

    @Override
    public ChatResponse chat(
            ChatRequest request,
            String conversationContext) {

        AIExecutionTimer timer =
                new AIExecutionTimer();

        log.info(
                "Starting AI request for session {}",
                request.getSessionId()
        );

        /*
         * Prompt Engineering
         */
        PromptExecution execution =
                promptEngine.build(
                        request,
                        conversationContext);

        String prompt =
                execution.getPrompt();

        /*
         * Record prompt history
         */
        promptHistoryService.record(
                execution.getMetadata(),
                prompt);

        log.debug("""
                ================ Generated Prompt ================

                {}

                ==================================================
                """, prompt);

        /*
         * AI execution
         */
        String aiResponse =
                aiClient.generateResponse(prompt);

        timer.stop();

        log.info(
                "AI response generated successfully for session {} in {}",
                request.getSessionId(),
                timer.getExecutionTimeWithUnit()
        );

        log.debug(
                "Response Length : {}",
                aiResponse.length()
        );

        return ChatResponse.builder()
                .response(aiResponse)
                .build();
    }
}