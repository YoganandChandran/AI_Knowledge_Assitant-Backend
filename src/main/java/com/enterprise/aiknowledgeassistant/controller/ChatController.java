package com.enterprise.aiknowledgeassistant.controller;

import com.enterprise.aiknowledgeassistant.dto.ChatRequest;
import com.enterprise.aiknowledgeassistant.dto.ChatResponse;
import com.enterprise.aiknowledgeassistant.service.ChatOrchestratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    private final ChatOrchestratorService chatOrchestratorService;

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        log.info(
                "Received AI request. Message Length : {}",
                request.getMessage().length()
        );
        return chatOrchestratorService.chat(request);
    }
}
