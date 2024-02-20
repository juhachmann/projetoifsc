package com.github.juhachmann.estagios.core.entities;

public class UserSettings {

    private boolean expiringVagaNotification = true;
    private boolean inactivityNotification = true;

    public boolean isExpiringVagaNotification() {
        return expiringVagaNotification;
    }

    public void setExpiringVagaNotification(boolean expiringVagaNotification) {
        this.expiringVagaNotification = expiringVagaNotification;
    }

    public boolean isInactivityNotification() {
        return inactivityNotification;
    }

    public void setInactivityNotification(boolean inactivityNotification) {
        this.inactivityNotification = inactivityNotification;
    }


}
