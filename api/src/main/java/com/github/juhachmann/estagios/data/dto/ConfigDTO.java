package com.github.juhachmann.estagios.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Validated
public class ConfigDTO extends RepresentationModel<ConfigDTO> implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private long key;
	
	private long ownerId;
	
	@NotEmpty
	@Valid
	private List<@Valid ConfigSettingsDTO> configs;
		
	
	public ConfigDTO() {
		super();
		configs = new ArrayList<>();
	}


	public ConfigDTO(long key, long ownerId, @NotEmpty List<@Valid ConfigSettingsDTO> settings) {
		super();
		this.key = key;
		this.ownerId = ownerId;
		this.configs = settings;
	}


	public long getKey() {
		return key;
	}


	public void setKey(long key) {
		this.key = key;
	}


	public long getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}


	public List<ConfigSettingsDTO> getSettings() {
		return configs;
	}


	public void setSettings(List<ConfigSettingsDTO> settings) {
		this.configs = settings;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key, ownerId, configs);
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
		ConfigDTO other = (ConfigDTO) obj;
		return key == other.key && ownerId == other.ownerId && Objects.equals(configs, other.configs);
	}
	

	
	
}


	
