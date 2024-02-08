package com.github.juhachmann.estagios.api.resources.shared;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



/**
 * Simple POJO for inserting contact information
 * 
 * Minimal Required fields: email, phone
 * 
 */

@Validated
@Schema(name="Contact", description = "Informações de contato")
public class ContactDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Email válido", example = "rh@nobanks.com", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	@Email
	private String email;
	
	@Schema(description = "Telefone válido", example = "48 3555-5500", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String phone;
	
	
	public ContactDTO() {
		super();
	}
	
	public ContactDTO(@NotBlank @Email String email,
			@NotBlank String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(@NotBlank @Email String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(@NotBlank String phone) {
		this.phone = phone;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, phone);
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
		ContactDTO other = (ContactDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(phone, other.phone);
	}

	
	
}
