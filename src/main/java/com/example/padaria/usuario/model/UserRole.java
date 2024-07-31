package com.example.padaria.usuario.model;

public enum UserRole {
    ROOT("root"),
    USER("user"),

    EMPLOYEE("employee");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

