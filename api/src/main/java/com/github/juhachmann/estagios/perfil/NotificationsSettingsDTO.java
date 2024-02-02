package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Validated
public class NotificationsSettingsDTO implements SettingsDTO, Serializable {
	
//	private List<SettingsDTO> settings = new ArrayList<>();

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private boolean expiringData;
	@NotNull
	private boolean moderation;
	@NotNull
	private boolean inactivity;
	
	
	public NotificationsSettingsDTO() {
		super();
		this.expiringData = true;
		this.moderation = true;
		this.inactivity = true;		
	//	this.settings = settings;
	}

	public NotificationsSettingsDTO(@NotNull boolean expiringData, @NotNull boolean moderation,
			@NotNull boolean inactivity) {
		super();
		this.expiringData = expiringData;
		this.moderation = moderation;
		this.inactivity = inactivity;
		//this.settings = settings;
	}

	public boolean isExpiringData() {
		return expiringData;
	}

	public void setExpiringData(boolean expiringData) {
		this.expiringData = expiringData;
	}

	public boolean isModeration() {
		return moderation;
	}

	public void setModeration(boolean moderation) {
		this.moderation = moderation;
	}

	public boolean isInactivity() {
		return inactivity;
	}

	public void setInactivity(boolean inactivity) {
		this.inactivity = inactivity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expiringData, inactivity, moderation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationsSettingsDTO other = (NotificationsSettingsDTO) obj;
		return expiringData == other.expiringData && inactivity == other.inactivity && moderation == other.moderation;
	}
	
	
	
}
