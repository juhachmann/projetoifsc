package com.github.juhachmann.estagios.resources.authUserConfig;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.juhachmann.estagios.exceptions.InvalidException;
import com.github.juhachmann.estagios.utils.Validatable;
import com.github.juhachmann.estagios.utils.ValidationHelper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Simple POJO to insert user configurations
 * 
 */
@Schema(name="Config", description = "User configurations")
@Validated
public class AuthUserConfigDTO extends RepresentationModel<AuthUserConfigDTO> implements Serializable, Validatable {


	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private long key;
	
	@JsonIgnore
	private long ownerId;
	
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description="User configs")
	@NotNull
	@Valid
	private NotificationsSettingsDTO notifications;
	

	public AuthUserConfigDTO() {
		super();
		this.notifications = new NotificationsSettingsDTO();
	}


	public AuthUserConfigDTO(long key, long ownerId, @NotNull @Valid NotificationsSettingsDTO notifications) {
		super();
		this.key = key;
		this.ownerId = ownerId;
		this.notifications = notifications;
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


	public NotificationsSettingsDTO getNotifications() {
		return notifications;
	}


	public void setNotifications(NotificationsSettingsDTO notifications) {
		this.notifications = notifications;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key);
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
		AuthUserConfigDTO other = (AuthUserConfigDTO) obj;
		return key == other.key && Objects.equals(notifications, other.notifications) && ownerId == other.ownerId;
	}


	@Override
	public void validate() throws InvalidException {
		ValidationHelper.validate(this);
	}


	

	
	
}


	
