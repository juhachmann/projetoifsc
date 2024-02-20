package com.github.juhachmann.estagios.apirest.resources.userPerfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.juhachmann.estagios.apirest.exceptions.InvalidException;
import com.github.juhachmann.estagios.apirest.utils.Validatable;
import com.github.juhachmann.estagios.apirest.utils.ValidationHelper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/**
 * 
 * Simple POJO to transfer public perfil info. 
 * 
 * Required fields: name, ie, areas 
 * 
 */

@Schema(description = "Perfil público da instituição ou empresa")
@JsonPropertyOrder("id")
@Validated

public class UserPerfilDTO extends RepresentationModel<UserPerfilDTO> implements Serializable, Validatable {

	private static final long serialVersionUID = 1L;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id da instituição ou empresa", example = "15")
	@JsonProperty("id")
	private Long key;
	
	@Schema(description = "Nome da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED, example = "NoBanks")
	@NotBlank
	private String name;
	
	@Schema(description = "É uma Instituição de Ensino (IE) ?", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "true, false", example = "false")
	@NotNull
	private boolean ie;
			
	@Schema(description = "Lista com as principais áreas de atuação da instituição ou empresa, separadas por vírgula", example="[\"Fintech\", \"Tecnologia\"]", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotEmpty
	private List<@NotBlank String> areas;
	
	@Schema(description = "Descrição da instituição ou empresa, com qualquer informação extra que você considere relevante", example="Somos uma importante fintech no mercado")
	private String description;

	@Schema(description = "Website da instituição ou empresa", example="www.nobanks.com")
	private String website;
	
	@Schema(description = "Lista de URLs das redes sociais da instituição ou empresa", example = "[\"https://linkedin.com/nobanks\", \"instagram.com/nobanks\"]")
	private List<@NotNull String> socialMedia;
	
	// TODO - Descobrir como tirar o _links do esquema que aparece no Swagger, ou, ao menos , definir um exemplo concreto

	
	public UserPerfilDTO() {
		this.ie = false;
		this.areas = new ArrayList<>();
		this.socialMedia = new ArrayList<>();
	}

	public UserPerfilDTO(Long key, @NotBlank String name, @NotNull boolean ie, @NotEmpty List<@NotBlank String> areas,
			String description, String website, List<@NotNull String> socialMedia) {
		super();
		this.key = key;
		this.name = name;
		this.ie = ie;
		this.areas = areas;
		this.description = description;
		this.website = website;
		this.socialMedia = socialMedia;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
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

	public List<String> getAreas() {
		return areas;
	}

	public void setAreas(List<String> areas) {
		this.areas = areas;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<String> getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(List<String> socialMedia) {
		this.socialMedia = socialMedia;
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
		UserPerfilDTO other = (UserPerfilDTO) obj;
		return Objects.equals(areas, other.areas) && Objects.equals(description, other.description) && ie == other.ie
				&& Objects.equals(key, other.key) && Objects.equals(name, other.name)
				&& Objects.equals(socialMedia, other.socialMedia) && Objects.equals(website, other.website);
	}

	@Override
	public void validate() throws InvalidException {
		//new ValidationHelper<UserPerfilDTO>().validate(this);
		ValidationHelper.validate(this);
	}

	
	

	
	
	


}
