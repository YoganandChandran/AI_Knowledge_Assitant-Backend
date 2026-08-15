package com.enterprise.aiknowledgeassistant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "google_id")
    private String googleId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(name = "profile_picture")
    private String profilePicture;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<ChatSession> chatSessions;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<PromptHistory> promptHistories;

}