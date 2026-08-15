package com.enterprise.aiknowledgeassistant.service;

import com.enterprise.aiknowledgeassistant.dto.session.CreateSessionRequest;
import com.enterprise.aiknowledgeassistant.dto.session.SessionResponse;
import com.enterprise.aiknowledgeassistant.dto.session.UpdateSessionRequest;

import java.util.List;
import java.util.UUID;

public interface SessionService {

    SessionResponse createSession(CreateSessionRequest request);

    List<SessionResponse> getAllSessions(UUID userId,
                                         String guestSessionId);

    SessionResponse getSession(UUID sessionId);

    SessionResponse updateSession(UUID sessionId,
                                  UpdateSessionRequest request);

    void deleteSession(UUID sessionId);

}