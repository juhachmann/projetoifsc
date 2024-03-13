package com.github.projetoifsc.pi.apirest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder(value = {"id", "nome", "links"})
public class AreaDTO extends RepresentationModel<AreaDTO> implements Serializable  {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "1")
    private String id;

    @JsonIgnore
    private UserDTO owner;

    @JsonProperty(value = "nome", required = true)
    @Schema(description = "Nome da área", example = "Educação",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String name;


    public AreaDTO(String id, UserDTO owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(hidden = true)
    public Links getLinks() {
        return super.getLinks();
    }

}
