package com.github.projetoifsc.pi.apirest.dto;

import java.io.Serializable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder(value = {"id", "titulo", "entidade", "links"})
@Schema(name="Vaga")
@Validated
public class VagaDTO extends RepresentationModel<VagaDTO> implements Serializable {

	@JsonProperty("id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
	private String id;

	@JsonProperty("criadorId")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@NotNull
	@Valid
	private UserDTO owner;

	@JsonProperty("titulo")
	@Schema(example="Vaga de desenhista Junior")
	@NotBlank
	private String title;

	public VagaDTO() {}

	public VagaDTO(String id, UserDTO owner, String title) {
		this.id = id;
		this.owner = owner;
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Schema(hidden = true)
	public Links getLinks() {
		return super.getLinks();
	}


}
