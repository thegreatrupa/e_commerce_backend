package com.example.auth_service.dto;

import com.example.auth_service.entity.Role;

public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole(){return role;}

    public void setRole(String role){
        this.role = role;
    }
}
