package com.github.juhachmann.estagios.api.resources.shared;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Validated

/**
 * Simple POJO for inserting address information
 * 
 * Minimal Required fields: city, state, country
 * 
 */
@Schema(name="Address", description = "Informações de endereço")
public class LocalizacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Schema(example = "Rua Hercílio Luz, nº 570, Centro", description = "Endereço principal das vagas que serão oferecidas no sistema. Caso necessário, no momento de cadastro de novas vagas, é possível definir endereços específicos")
	private String line;

	@Schema(description = "Cidade", example = "Florianópolis", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String city;
	
	@Schema(description = "Nome completo do estado", example = "Santa Catarina", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String state;
	
	@Schema(description = "País", example = "Brasil", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	private String country;

	public LocalizacaoDTO() {
		super();
	}
	
	public LocalizacaoDTO(String endereco, @NotBlank String cidade, @NotBlank String estado, @NotBlank String pais) {
		super();
		this.line = endereco;
		this.city = cidade;
		this.state = estado;
		this.country = pais;
	}

	
	public String getLine() {
		return line;
	}

	public void setLine(String addressLine) {
		this.line = addressLine;
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
		result = prime * result + Objects.hash(city, line, state, country);
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
		return Objects.equals(city, other.city) && Objects.equals(line, other.line)
				&& Objects.equals(state, other.state) && Objects.equals(country, other.country);
	}
	
}
