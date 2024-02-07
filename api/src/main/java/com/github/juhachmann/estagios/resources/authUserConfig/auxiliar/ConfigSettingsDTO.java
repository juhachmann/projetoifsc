package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Simple POJO to group configuration sub settings under a topic name
 * 
 */

@Validated
public class ConfigSettingsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "notificacoes", description="Configurações de notificações do sistema")
	@NotNull
	private String topic;
	
	@NotNull
	private SettingsDTO settings;
	
	
	public ConfigSettingsDTO() {
		super();
	}
	
	public ConfigSettingsDTO(@NotNull String topic, @NotNull SettingsDTO settings) {
		super();
		this.topic = topic;
		this.settings = settings;
	}
	
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	
	public SettingsDTO getSettings() {
		return settings;
	}
	public void setSettings(SettingsDTO settings) {
		this.settings = settings;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(settings, topic);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigSettingsDTO other = (ConfigSettingsDTO) obj;
		return Objects.equals(settings, other.settings) && Objects.equals(topic, other.topic);
	}
	
	
}
