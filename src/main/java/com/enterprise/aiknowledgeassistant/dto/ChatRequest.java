package com.enterprise.aiknowledgeassistant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 5000, message = "Message cannot exceed 5000 characters")
    private String message;

    private String role;

    private Boolean markdownEnabled;

    private Boolean structuredOutput;

    private UUID sessionId;

    private UUID userId;

    private String guestSessionId;

}
