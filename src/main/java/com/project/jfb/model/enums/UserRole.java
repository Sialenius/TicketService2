package com.project.jfb.model.enums;

public enum UserRole {
    CLIENT("Client"),
    ADMIN("Admin");

    public String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
 }
