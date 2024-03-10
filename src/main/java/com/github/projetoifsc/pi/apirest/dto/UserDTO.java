package com.github.projetoifsc.pi.apirest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Schema(name = "Organização", description = "Sumário da Organização")
@JsonPropertyOrder(value = {"id", "nome", "instituicaoDeEnsino"})
@Validated
public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Id", example="123")
    private String key;

    @JsonProperty(value = "nome", required = true)
    @Schema(description = "Nome da Organização", requiredMode = Schema.RequiredMode.REQUIRED, example = "Nobanks")
    @NotBlank
    private String name;

    @JsonProperty(value = "instituicaoDeEnsino", required = true)
    @Schema(description = "É uma Instituição de Ensino?", requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean", allowableValues = {"true", "false"}, example = "false")
    private boolean ie = false;


    public UserDTO(String key, String name, boolean ie) {
        this.key = key;
        this.name = name;
        this.ie = ie;
    }

    public String getId() {
        return key;
    }

    public void setId(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }


}
