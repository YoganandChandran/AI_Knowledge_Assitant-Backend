package com.enterprise.aiknowledgeassistant.ai.optimization;

import org.springframework.stereotype.Component;

@Component
public class PromptOptimizerImpl implements PromptOptimizer {

    @Override
    public String optimize(String prompt) {

        if (prompt == null) {
            return null;
        }

        String optimized = prompt;

        // Trim leading and trailing spaces
        optimized = optimized.trim();

        // Normalize Windows/Mac line endings
        optimized = optimized.replace("\r\n", "\n")
                .replace("\r", "\n");

        // Replace multiple spaces with a single space
        optimized = optimized.replaceAll("[ ]{2,}", " ");

        // Remove multiple blank lines
        optimized = optimized.replaceAll("\\n{3,}", "\n\n");

        // Remove control characters except newline and tab
        optimized = optimized.replaceAll("[\\p{Cntrl}&&[^\n\t]]", "");

        return optimized;
    }

}