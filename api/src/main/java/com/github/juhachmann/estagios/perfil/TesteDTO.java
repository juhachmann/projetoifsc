package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name="Teste")
public class TesteDTO extends RepresentationModel<TesteDTO> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Meu nome", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonProperty("myName")
	@NotBlank
	private String name;
	
	public TesteDTO() {
	}

	public TesteDTO(@NotBlank String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
