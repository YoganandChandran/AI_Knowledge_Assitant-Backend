package com.enterprise.aiknowledgeassistant.ai.engine;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PromptMetadata {

    private final UUID sessionId;

    private final UUID userId;

    private final String guestSessionId;

    private final String role;

    private final String version;

    private final boolean markdownEnabled;

    private final boolean structuredOutput;

    private final int promptLength;
}