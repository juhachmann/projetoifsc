package com.github.juhachmann.estagios.core.entities;

public class VagaSettings {

    private Integer expiresInDays = 0;
    private Integer renovateInFays = 0;

    public VagaSettings() {
    }

    public VagaSettings(Integer expiresInDays, Integer renovateInFays) {
        this.expiresInDays = expiresInDays;
        this.renovateInFays = renovateInFays;
    }

    public Integer getExpiresInDays() {
        return expiresInDays;
    }

    public void setExpiresInDays(Integer expiresInDays) {
        this.expiresInDays = expiresInDays;
    }

    public Integer getRenovateInFays() {
        return renovateInFays;
    }

    public void setRenovateInFays(Integer renovateInFays) {
        this.renovateInFays = renovateInFays;
    }
}
