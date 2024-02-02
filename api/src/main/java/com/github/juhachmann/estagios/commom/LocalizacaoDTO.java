package com.github.juhachmann.estagios.commom;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
public class LocalizacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String addressLine;

	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String country;

	public LocalizacaoDTO() {
		super();
	}
	
	public LocalizacaoDTO(String endereco, @NotBlank String cidade, @NotBlank String estado, @NotBlank String pais) {
		super();
		this.addressLine = endereco;
		this.city = cidade;
		this.state = estado;
		this.country = pais;
	}

	
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(city, addressLine, state, country);
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
		LocalizacaoDTO other = (LocalizacaoDTO) obj;
		return Objects.equals(city, other.city) && Objects.equals(addressLine, other.addressLine)
				&& Objects.equals(state, other.state) && Objects.equals(country, other.country);
	}
	
}
