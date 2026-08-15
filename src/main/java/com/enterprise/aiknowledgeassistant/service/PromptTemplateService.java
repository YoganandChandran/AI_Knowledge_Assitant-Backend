package com.enterprise.aiknowledgeassistant.service;

public interface PromptTemplateService {

    String getSystemPrompt();

    String getDeveloperPrompt();

    String getArchitectPrompt();

    String getInterviewerPrompt();

    String getTeacherPrompt();

    String getOutputFormatPrompt();

    String getJsonOutputPrompt();

}
