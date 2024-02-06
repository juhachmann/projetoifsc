package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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

@Schema(description = "Perfil público do usuário")
@JsonPropertyOrder("id")
@Validated
public class PerfilPublicoDTO extends RepresentationModel<PerfilPublicoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id da instituição ou empresa", example = "15")
	@JsonProperty("id")
	protected Long key;
	
	@Schema(name = "Nome da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED, example = "NoBanks")
	@NotBlank
	protected String name;
	
	@Schema(name = "É uma Instituição de Ensino (IE) ?", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "true, false", example = "false")
	@NotNull
	protected boolean ie;

	@Schema(name = "Website da instituição ou empresa", example="www.nobanks.com")
	@URL
	protected String website;
	
	@Schema(name = "Lista de URLs das redes sociais da instituição ou empresa", example = "['https://linkedin.com/nobanks', 'instagram.com/nobanks']")
	protected List<@NotNull @URL String> socialMedia;
	
	@Schema(name = "Lista com as principais áreas de atuação da instituição ou empresa, separadas por vírgula", example="['Fintech', 'Tecnologia']", description = "Forneça ao menos uma área", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotEmpty
	protected List<@NotBlank String> areas;
	
	@Schema(name = "Informação extra", description = "Descrição da instituição ou empresa, com qualquer informação extra que você considere relevante", example="Somos uma importante fintech no mercado")
	protected String extraInfo;


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


	public List<String> getAreas() {
		return areas;
	}


	public void setAreas(List<String> areas) {
		this.areas = areas;
	}


	public String getExtraInfo() {
		return extraInfo;
	}


	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	
	
	public PerfilPublicoDTO() {
		this.socialMedia = new ArrayList<>();
		this.areas =  new ArrayList<>();	
	}

	public PerfilPublicoDTO(@NotBlank String name, @NotNull boolean ie, @URL String website,
			List<@NotNull @URL String> socialMedia, @NotEmpty List<@NotBlank String> areas, String extraInfo) {
		this.name = name;
		this.ie = ie;
		this.website = website;
		this.socialMedia = socialMedia;
		this.areas = areas;
		this.extraInfo = extraInfo;
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
		PerfilPublicoDTO other = (PerfilPublicoDTO) obj;
		return Objects.equals(areas, other.areas) && Objects.equals(ie, other.ie)
				&& Objects.equals(extraInfo, other.extraInfo) 
				&& Objects.equals(key, other.key) && Objects.equals(name, other.name)
				&& Objects.equals(socialMedia, other.socialMedia) && Objects.equals(website, other.website);
	}

}
