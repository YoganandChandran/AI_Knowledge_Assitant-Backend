package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.ai.engine.PromptMetadata;
import com.enterprise.aiknowledgeassistant.entity.PromptHistory;
import com.enterprise.aiknowledgeassistant.repository.PromptHistoryRepository;
import com.enterprise.aiknowledgeassistant.service.PromptHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromptHistoryServiceImpl
        implements PromptHistoryService {

    private final PromptHistoryRepository promptHistoryRepository;

    @Override
    public void record(
            PromptMetadata metadata,
            String prompt) {

        if (metadata == null) {
            throw new IllegalArgumentException(
                    "Prompt metadata cannot be null."
            );
        }

        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException(
                    "Prompt cannot be blank."
            );
        }

        PromptHistory history =
                PromptHistory.builder()
                        .guestSessionId(
                                metadata.getGuestSessionId())
                        .prompt(prompt)
                        .version(metadata.getVersion())
                        .role(metadata.getRole())
                        .promptLength(
                                metadata.getPromptLength())
                        .markdownEnabled(
                                metadata.isMarkdownEnabled())
                        .structuredOutput(
                                metadata.isStructuredOutput())
                        .build();

        promptHistoryRepository.save(history);

        log.debug(
                "Prompt history recorded. Session={}, Version={}",
                metadata.getSessionId(),
                metadata.getVersion()
        );
    }
}