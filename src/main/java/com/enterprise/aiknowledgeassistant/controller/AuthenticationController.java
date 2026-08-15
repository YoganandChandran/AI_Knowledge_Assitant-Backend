package com.enterprise.aiknowledgeassistant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @GetMapping("/me")
    public Map<String, Object> currentUser(
            Authentication authentication) {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        return Map.of(
                "authenticated", true,
                "name", oauth2User.getAttribute("name"),
                "email", oauth2User.getAttribute("email"),
                "picture", oauth2User.getAttribute("picture")
        );
    }
}