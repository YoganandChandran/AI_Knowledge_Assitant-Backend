package com.enterprise.aiknowledgeassistant.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponse {

    private UUID sessionId;

    private UUID messageId;

    private String response;
}
