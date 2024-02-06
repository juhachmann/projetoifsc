package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Contains the available settings for user configurations related with system notifications
 * All values are true by default
 */
@Schema(name="Notification Settings", description = "Configura as notificações que o usuário recebe do sistema")
@Validated
public class NotificationsSettingsDTO implements SettingsDTO, Serializable { 
	
//	private List<SettingsDTO> settings = new ArrayList<>();

	private static final long serialVersionUID = 1L;
	
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, example="true", allowableValues="true, false", description = "Recebe notificações sobre vagas que estão próximas a serem excluídas do sistema, para que o usuário possa renovar a oferta das vagas")
	@NotNull
	private boolean expiringData;
	
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, example="true", allowableValues="true, false", description = "Recebe notificações relacionadas a atividades de moderação de vagas publicadas, tais como denúncias")
	@NotNull
	private boolean moderation;
	
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, example="true", allowableValues="true, false", description = "Recebe uma notificação após um período de inatividade, para lhe lembrar de publicar uma vaga no sistema")
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
