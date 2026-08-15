package com.enterprise.aiknowledgeassistant.ai.role.strategy;

import com.enterprise.aiknowledgeassistant.ai.role.AIRole;
import com.enterprise.aiknowledgeassistant.ai.role.RolePromptStrategy;
import com.enterprise.aiknowledgeassistant.service.PromptTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArchitectPromptStrategy implements RolePromptStrategy {

    private final PromptTemplateService promptTemplateService;

    @Override
    public AIRole getRole() {
        return AIRole.ARCHITECT;
    }

    @Override
    public String getPrompt() {
        return promptTemplateService.getArchitectPrompt();
    }

}