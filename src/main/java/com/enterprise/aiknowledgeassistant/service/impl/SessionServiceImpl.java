package com.enterprise.aiknowledgeassistant.service.impl;

import com.enterprise.aiknowledgeassistant.dto.session.CreateSessionRequest;
import com.enterprise.aiknowledgeassistant.dto.session.SessionResponse;
import com.enterprise.aiknowledgeassistant.dto.session.UpdateSessionRequest;
import com.enterprise.aiknowledgeassistant.entity.ChatSession;
import com.enterprise.aiknowledgeassistant.entity.SessionStatus;
import com.enterprise.aiknowledgeassistant.mapper.SessionMapper;
import com.enterprise.aiknowledgeassistant.repository.ChatSessionRepository;
import com.enterprise.aiknowledgeassistant.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SessionServiceImpl implements SessionService {

    private final ChatSessionRepository sessionRepository;

    @Override
    public SessionResponse createSession(CreateSessionRequest request) {

        log.info(
                "Creating Session Title : {}",
                request.getTitle());

        ChatSession session = ChatSession.builder()
                .title(request.getTitle() == null || request.getTitle().isBlank()
                        ? "New Chat"
                        : request.getTitle())
                .guestSessionId(request.getGuestSessionId())
                .status(SessionStatus.ACTIVE)
                .summary(null)
                .lastMessageAt(LocalDateTime.now())
                .build();

        session = sessionRepository.save(session);

        log.info("Session Created Successfully : {}", session.getId());

        return SessionMapper.toResponse(session);

    }

    @Override
    public List<SessionResponse> getAllSessions(UUID userId,
                                                String guestSessionId) {

        List<ChatSession> sessions;

        if (userId != null) {

            sessions = sessionRepository.findByUserId(userId);

        } else {

            sessions = sessionRepository.findByGuestSessionId(guestSessionId);

        }

        return sessions.stream()
                .map(SessionMapper::toResponse)
                .toList();

    }

    @Override
    public SessionResponse getSession(UUID sessionId) {

        ChatSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Session not found : " + sessionId));

        return SessionMapper.toResponse(session);

    }

    @Override
    public SessionResponse updateSession(UUID sessionId,
                                         UpdateSessionRequest request) {

        ChatSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Session not found"));

        session.setTitle(request.getTitle());

        session = sessionRepository.save(session);

        return SessionMapper.toResponse(session);

    }

    @Override
    public void deleteSession(UUID sessionId) {

        ChatSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Session not found"));

        session.setStatus(SessionStatus.DELETED);

        sessionRepository.save(session);

    }

}