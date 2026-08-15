package com.enterprise.aiknowledgeassistant.ai.client;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenRouterAIClient implements AIClient {

    private final ChatClient chatClient;

    @Override
    public String generateResponse(String prompt) {

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }

}
