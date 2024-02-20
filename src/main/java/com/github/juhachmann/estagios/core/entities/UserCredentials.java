package com.github.juhachmann.estagios.core.entities;

import jakarta.validation.constraints.NotBlank;

// TODO : Como a senha é recuperada do banco de dados ? Vem encriptada, claro, né?

public class UserCredentials {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UserCredentials() {
    }

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
