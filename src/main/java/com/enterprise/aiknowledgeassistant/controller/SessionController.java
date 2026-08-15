package com.enterprise.aiknowledgeassistant.controller;

import com.enterprise.aiknowledgeassistant.dto.session.CreateSessionRequest;
import com.enterprise.aiknowledgeassistant.dto.session.SessionResponse;
import com.enterprise.aiknowledgeassistant.dto.session.UpdateSessionRequest;
import com.enterprise.aiknowledgeassistant.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public SessionResponse createSession(
            @Valid @RequestBody CreateSessionRequest request) {

        return sessionService.createSession(request);

    }

    @GetMapping
    public List<SessionResponse> getSessions(

            @RequestParam(required = false)
            UUID userId,

            @RequestParam(required = false)
            String guestSessionId) {

        return sessionService.getAllSessions(
                userId,
                guestSessionId
        );

    }

    @GetMapping("/{id}")
    public SessionResponse getSession(
            @PathVariable UUID id) {

        return sessionService.getSession(id);

    }

    @PutMapping("/{id}")
    public SessionResponse updateSession(

            @PathVariable UUID id,

            @Valid
            @RequestBody
            UpdateSessionRequest request) {

        return sessionService.updateSession(id,
                request);

    }

    @DeleteMapping("/{id}")
    public void deleteSession(
            @PathVariable UUID id) {

        sessionService.deleteSession(id);

    }

}