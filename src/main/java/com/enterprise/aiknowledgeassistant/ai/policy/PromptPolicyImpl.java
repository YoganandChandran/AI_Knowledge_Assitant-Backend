package com.enterprise.aiknowledgeassistant.ai.policy;

import com.enterprise.aiknowledgeassistant.exception.PromptValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PromptPolicyImpl implements PromptPolicy {

    @Override
    public String apply(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            throw new PromptValidationException(
                    "Final prompt cannot be blank."
            );
        }

        return prompt.trim();
    }
}