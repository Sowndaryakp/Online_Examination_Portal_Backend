package com.java.backend.model;

public class AuthResponse {
    private String message;
    private String token;
    private boolean approved;

    public AuthResponse() {
    }

    public AuthResponse(String message, String token, boolean approved) {
        this.message = message;
        this.token = token;
        this.approved = approved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
