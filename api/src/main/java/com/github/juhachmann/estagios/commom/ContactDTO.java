package com.github.juhachmann.estagios.commom;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
public class ContactDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String telefone;
	
	
	public ContactDTO() {
		super();
	}
	
	public ContactDTO(@NotBlank @Email String email,
			@NotBlank String telefone) {
		super();
		this.email = email;
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(@NotBlank @Email String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(@NotBlank String telefone) {
		this.telefone = telefone;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, telefone);
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
		return Objects.equals(email, other.email) && Objects.equals(telefone, other.telefone);
	}

	
	
}
