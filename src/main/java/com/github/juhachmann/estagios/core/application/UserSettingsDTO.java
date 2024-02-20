package com.github.juhachmann.estagios.core.application;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.github.juhachmann.estagios.core.entities.User}
 */
public class UserSettingsDTO implements Serializable {

    private static final long serialVersionUID = -6570235719610833594L;
    private boolean settingsExpiringVagaNotification = true;
    private boolean settingsInactivityNotification = true;

    public UserSettingsDTO() {
    }

    public UserSettingsDTO(boolean settingsExpiringVagaNotification, boolean settingsInactivityNotification) {
        this.settingsExpiringVagaNotification = settingsExpiringVagaNotification;
        this.settingsInactivityNotification = settingsInactivityNotification;
    }

    public boolean getSettingsExpiringVagaNotification() {
        return settingsExpiringVagaNotification;
    }

    public void setSettingsExpiringVagaNotification(boolean settingsExpiringVagaNotification) {
        this.settingsExpiringVagaNotification = settingsExpiringVagaNotification;
    }

    public boolean getSettingsInactivityNotification() {
        return settingsInactivityNotification;
    }

    public void setSettingsInactivityNotification(boolean settingsInactivityNotification) {
        this.settingsInactivityNotification = settingsInactivityNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettingsDTO entity = (UserSettingsDTO) o;
        return Objects.equals(this.settingsExpiringVagaNotification, entity.settingsExpiringVagaNotification) &&
                Objects.equals(this.settingsInactivityNotification, entity.settingsInactivityNotification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingsExpiringVagaNotification, settingsInactivityNotification);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "settingsExpiringVagaNotification = " + settingsExpiringVagaNotification + ", " +
                "settingsInactivityNotification = " + settingsInactivityNotification + ")";
    }
}