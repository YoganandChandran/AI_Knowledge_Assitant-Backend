package com.enterprise.aiknowledgeassistant.ai;

import com.enterprise.aiknowledgeassistant.ai.role.RolePromptResolver;
import com.enterprise.aiknowledgeassistant.config.PromptConfig;
import com.enterprise.aiknowledgeassistant.dto.ChatRequest;
import com.enterprise.aiknowledgeassistant.service.PromptTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromptBuilder {

    private final PromptTemplateService promptTemplateService;
    private final PromptConfig promptConfig;
    private final RolePromptResolver rolePromptResolver;

    public String buildPrompt(ChatRequest request,
                              String conversationContext) {

        StringBuilder prompt = new StringBuilder();

        prompt.append(promptTemplateService.getSystemPrompt());

        prompt.append("\n\n");

        String role = request.getRole();

        if (role == null || role.isBlank()) {
            role = promptConfig.getDefaultRole();
        }

        prompt.append(
                rolePromptResolver.resolvePrompt(role)
        );

        prompt.append("\n\n");

        if (!conversationContext.isBlank()) {

            prompt.append(conversationContext);

            prompt.append("\n\n");

        }

        boolean markdownEnabled =
                request.getMarkdownEnabled() != null
                        ? request.getMarkdownEnabled()
                        : promptConfig.isMarkdownEnabled();

        boolean structuredOutput =
                request.getStructuredOutput() != null
                        ? request.getStructuredOutput()
                        : promptConfig.isStructuredOutput();

        if (structuredOutput) {

            prompt.append(
                    promptTemplateService.getJsonOutputPrompt());

            prompt.append("\n\n");

        } else if (markdownEnabled) {

            prompt.append(
                    promptTemplateService.getOutputFormatPrompt());

            prompt.append("\n\n");

        }

        if (promptConfig.isIncludeBestPractices()) {

            prompt.append(
                    "Always include Best Practices.\n");

        }

        if (promptConfig.isIncludeSummary()) {

            prompt.append(
                    "Always include Summary.\n");

        }

        prompt.append("\nCurrent User Question:\n");

        prompt.append(request.getMessage());

        return prompt.toString();

    }

}