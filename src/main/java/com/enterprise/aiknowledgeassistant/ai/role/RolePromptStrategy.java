package com.enterprise.aiknowledgeassistant.ai.role;

public interface RolePromptStrategy {

    AIRole getRole();

    String getPrompt();

}
