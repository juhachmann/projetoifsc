package com.github.projetoifsc.pi.apirest.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder("id")
@Schema(name="Vaga")
@Validated
public class VagaDTO extends RepresentationModel<VagaDTO> implements Serializable{

	@JsonProperty("id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
	private String key;

	@JsonProperty("criadorId")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "NoBanks")
	private String ownerId;

	@JsonProperty("titulo")
	@Schema(example="Vaga de desenhista Junior")
	@NotBlank
	private String title;

	public VagaDTO() {}

	public VagaDTO(String key, String ownerId, String title) {
		this.key = key;
		this.ownerId = ownerId;
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
