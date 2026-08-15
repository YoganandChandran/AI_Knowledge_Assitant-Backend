package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;
import com.enterprise.aiknowledgeassistant.entity.ChatSession;
import com.enterprise.aiknowledgeassistant.entity.MessageRole;
import com.enterprise.aiknowledgeassistant.repository.ChatMessageRepository;
import com.enterprise.aiknowledgeassistant.service.ChatMessageService;
import com.enterprise.aiknowledgeassistant.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl
        implements ChatMessageService {

    private final ChatMessageRepository messageRepository;

    private final EmbeddingService embeddingService;


    @Override
    public ChatMessage saveUserMessage(ChatSession session,
                                       String message) {

        ChatMessage userMessage =
                ChatMessage.builder()
                        .chatSession(session)
                        .role(MessageRole.USER)
                        .content(message)
                        .build();

        ChatMessage savedMessage =
                messageRepository.save(userMessage);

        embeddingService.generateAndStoreEmbedding(
                savedMessage
        );

        return savedMessage;

    }

    @Override
    public ChatMessage saveAssistantMessage(ChatSession session,
                                            String response) {

        ChatMessage assistantMessage =
                ChatMessage.builder()
                        .chatSession(session)
                        .role(MessageRole.ASSISTANT)
                        .content(response)
                        .build();

        ChatMessage savedMessage =
                messageRepository.save(assistantMessage);

        embeddingService.generateAndStoreEmbedding(
                savedMessage
        );

        return savedMessage;

    }

    @Override
    public List<ChatMessage> getConversation(ChatSession session) {

        return messageRepository
                .findByChatSessionIdOrderByCreatedAtAsc(
                        session.getId());

    }

}