package com.example.auth_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    public RefreshToken() {}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
