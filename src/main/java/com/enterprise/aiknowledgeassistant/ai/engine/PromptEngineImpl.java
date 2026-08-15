package com.enterprise.aiknowledgeassistant.ai.engine;

import com.enterprise.aiknowledgeassistant.ai.PromptBuilder;
import com.enterprise.aiknowledgeassistant.ai.optimization.PromptOptimizer;
import com.enterprise.aiknowledgeassistant.ai.policy.PromptPolicy;
import com.enterprise.aiknowledgeassistant.ai.validation.PromptValidator;
import com.enterprise.aiknowledgeassistant.config.PromptConfig;
import com.enterprise.aiknowledgeassistant.dto.ChatRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PromptEngineImpl implements PromptEngine {

    private final PromptValidator promptValidator;

    private final PromptOptimizer promptOptimizer;

    private final PromptBuilder promptBuilder;

    private final PromptConfig promptConfig;

    private final PromptPolicy promptPolicy;

    @Override
    public PromptExecution build(
            ChatRequest request,
            String conversationContext) {

        log.debug(
                "Starting prompt engineering pipeline for session {}",
                request.getSessionId()
        );

        /*
         * 1. Validate
         */
        String validatedPrompt =
                promptValidator.validate(
                        request.getMessage());

        log.debug(
                "Prompt validation completed. Session={}, Length={}",
                request.getSessionId(),
                validatedPrompt.length()
        );

        /*
         * 2. Optimize
         */
        String optimizedPrompt =
                promptOptimizer.optimize(
                        validatedPrompt);

        log.debug(
                "Prompt optimization completed. Session={}, Length={}",
                request.getSessionId(),
                optimizedPrompt.length()
        );

        /*
         * 3. Preserve request metadata
         */
        ChatRequest promptRequest =
                ChatRequest.builder()
                        .sessionId(request.getSessionId())
                        .userId(request.getUserId())
                        .guestSessionId(
                                request.getGuestSessionId())
                        .message(optimizedPrompt)
                        .role(request.getRole())
                        .markdownEnabled(
                                request.getMarkdownEnabled())
                        .structuredOutput(
                                request.getStructuredOutput())
                        .build();

        /*
         * 4. Build final prompt
         */
        String finalPrompt =
                promptBuilder.buildPrompt(
                        promptRequest,
                        conversationContext);

        if (promptConfig.isPolicyEnabled()) {

            finalPrompt =
                    promptPolicy.apply(finalPrompt);

        }

        /*
         * 5. Resolve effective configuration
         */
        String role =
                request.getRole();

        if (role == null || role.isBlank()) {
            role = promptConfig.getDefaultRole();
        }

        boolean markdownEnabled =
                request.getMarkdownEnabled() != null
                        ? request.getMarkdownEnabled()
                        : promptConfig.isMarkdownEnabled();

        boolean structuredOutput =
                request.getStructuredOutput() != null
                        ? request.getStructuredOutput()
                        : promptConfig.isStructuredOutput();

        log.debug(
                "Prompt composition completed. Session={}, Length={}",
                request.getSessionId(),
                finalPrompt.length()
        );

        /*
         * 6. Build metadata
         */
        PromptMetadata metadata =
                PromptMetadata.builder()
                        .sessionId(
                                request.getSessionId())
                        .userId(
                                request.getUserId())
                        .guestSessionId(
                                request.getGuestSessionId())
                        .role(role)
                        .version(
                                promptConfig.getVersion())
                        .markdownEnabled(
                                markdownEnabled)
                        .structuredOutput(
                                structuredOutput)
                        .promptLength(
                                finalPrompt.length())
                        .build();

        log.info(
                "Prompt execution prepared. Session={}, Version={}, Role={}, Length={}",
                metadata.getSessionId(),
                metadata.getVersion(),
                metadata.getRole(),
                metadata.getPromptLength()
        );

        return PromptExecution.builder()
                .prompt(finalPrompt)
                .metadata(metadata)
                .build();
    }
}