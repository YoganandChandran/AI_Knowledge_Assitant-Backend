package com.enterprise.aiknowledgeassistant.ai.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RolePromptResolverImpl implements RolePromptResolver {

    private final Map<AIRole, RolePromptStrategy> strategyMap;

    public RolePromptResolverImpl(List<RolePromptStrategy> strategies) {

        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        RolePromptStrategy::getRole,
                        Function.identity()
                ));

    }

    @Override
    public String resolvePrompt(String role) {

        AIRole aiRole;

        try {

            aiRole = AIRole.valueOf(role.toUpperCase());

        } catch (Exception ex) {

            log.warn("Unknown role '{}'. Falling back to DEVELOPER.", role);

            aiRole = AIRole.DEVELOPER;

        }

        return strategyMap.get(aiRole).getPrompt();

    }

}