package com.enterprise.aiknowledgeassistant.ai.role.strategy;

import com.enterprise.aiknowledgeassistant.ai.role.AIRole;
import com.enterprise.aiknowledgeassistant.ai.role.RolePromptStrategy;
import com.enterprise.aiknowledgeassistant.service.PromptTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeveloperPromptStrategy implements RolePromptStrategy {

    private final PromptTemplateService promptTemplateService;

    @Override
    public AIRole getRole() {
        return AIRole.DEVELOPER;
    }

    @Override
    public String getPrompt() {
        return promptTemplateService.getDeveloperPrompt();
    }

}