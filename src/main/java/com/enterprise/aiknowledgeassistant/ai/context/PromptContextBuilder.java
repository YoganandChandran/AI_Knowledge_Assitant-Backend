package com.enterprise.aiknowledgeassistant.ai.context;

import com.enterprise.aiknowledgeassistant.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PromptContextBuilder {

    public String buildConversationContext(
            List<ChatMessage> history,
            List<ChatMessage> relevantMessages) {

        StringBuilder context =
                new StringBuilder();

        context.append("CONVERSATION HISTORY:\n");

        for (ChatMessage message : history) {

            context.append(message.getRole())
                    .append(": ")
                    .append(message.getContent())
                    .append("\n");
        }

        if (!relevantMessages.isEmpty()) {

            context.append("\nRELEVANT PREVIOUS CONTEXT:\n");

            for (ChatMessage message : relevantMessages) {

                context.append(message.getRole())
                        .append(": ")
                        .append(message.getContent())
                        .append("\n");
            }
        }

        return context.toString();
    }

}