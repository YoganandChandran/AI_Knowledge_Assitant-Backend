package com.enterprise.aiknowledgeassistant.ai.validation;

import com.enterprise.aiknowledgeassistant.exception.PromptValidationException;
import org.springframework.stereotype.Component;

@Component
public class PromptValidatorImpl implements PromptValidator {

    private static final int MIN_LENGTH = 3;

    private static final int MAX_LENGTH = 3000;

    private static final int MAX_CONSECUTIVE_CHARACTERS = 4;

    @Override
    public String validate(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            throw new PromptValidationException("Prompt cannot be blank.");
        }

        String trimmedPrompt = prompt.trim();

        if (trimmedPrompt.length() < MIN_LENGTH) {
            throw new PromptValidationException(
                    "Prompt must contain at least " + MIN_LENGTH + " characters."
            );
        }

        if (trimmedPrompt.length() > MAX_LENGTH) {
            throw new PromptValidationException(
                    "Prompt cannot exceed " + MAX_LENGTH + " characters."
            );
        }

        validateRepeatedCharacters(trimmedPrompt);

        return trimmedPrompt;
    }

    private void validateRepeatedCharacters(String prompt) {

        String regex = ".*(.)\\1{" + (MAX_CONSECUTIVE_CHARACTERS - 1) + ",}.*";

        if (prompt.matches(regex)) {
            throw new PromptValidationException(
                    "Prompt contains excessive repeated characters."
            );
        }
    }

}