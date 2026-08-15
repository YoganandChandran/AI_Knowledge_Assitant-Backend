package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.service.PromptTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class PromptTemplateServiceImpl implements PromptTemplateService {

    @Override
    public String getSystemPrompt() {
        return loadPrompt("prompts/system.st");
    }

    @Override
    public String getDeveloperPrompt() {
        return loadPrompt("prompts/developer.st");
    }

    @Override
    public String getArchitectPrompt() {
        return loadPrompt("prompts/architect.st");
    }

    @Override
    public String getInterviewerPrompt() {
        return loadPrompt("prompts/interviewer.st");
    }

    @Override
    public String getTeacherPrompt() {
        return loadPrompt("prompts/teacher.st");
    }

    @Override
    public String getOutputFormatPrompt(){
        return loadPrompt("prompts/output-format.st");
    }

    @Override
    public String getJsonOutputPrompt(){
        return loadPrompt("prompts/json-output.st");
    }

    private String loadPrompt(String path) {

        try {

            ClassPathResource resource = new ClassPathResource(path);

            return new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (IOException ex) {
            log.error(
                    "AI request failed : {}",
                    ex.getMessage(),
                    ex
            );
            throw new RuntimeException("Unable to load prompt : " + path, ex);
        }

    }

}