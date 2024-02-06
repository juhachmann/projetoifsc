package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.juhachmann.estagios.commom.ContactDTO;
import com.github.juhachmann.estagios.commom.LocalizacaoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * Simple POJO to transfer public perfil info. 
 * 
 * Required fields: name, ie, areas, cnpj, address, generalContact 
 * 
 */
@Schema(name="Profile", description = "Perfil da instituição ou empresa")
@JsonPropertyOrder("id")
public class PerfilDTO extends RepresentationModel<PerfilDTO> implements Serializable { // TODO Dúvida: não consegui estender do PerfilPublico pq tive que estender do RepresentationModel<> e estava dando erro lá na frente.. tem algum jeito de consertar isso?
	
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
	
	@Schema(description = "CNPJ válido da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
	@NotNull
	@CNPJ
	private String cnpj;
		
	@Schema(description = "Lista com as principais áreas de atuação da instituição ou empresa, separadas por vírgula", example="[\"Fintech\", \"Tecnologia\"]", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotEmpty
	private List<@NotBlank String> areas;
	
	@Schema(description = "Descrição da instituição ou empresa, com qualquer informação extra que você considere relevante", example="Somos uma importante fintech no mercado")
	private String description;

	@Schema(description = "Endereço principal da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull
	@Valid
	private LocalizacaoDTO address;
	
	@Schema(description = "Contato principal da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull
	@Valid
	private ContactDTO generalContact;
	
	@Schema(description = "Contato padrão para envio de currículos. Caso este campo não seja informado, iremos considerar o contato principal. Além disto, no cadastro de vagas é possível inserir contatos para as vagas em específico. Será mostrado apenas no momento de visualização das vagas que você publicar")
	@Valid
	private ContactDTO applicationContact;

	@Schema(description = "Website da instituição ou empresa", example="www.nobanks.com")
	private String website;
	
	@Schema(description = "Lista de URLs das redes sociais da instituição ou empresa", example = "[\"https://linkedin.com/nobanks\", \"instagram.com/nobanks\"]")
	private List<@NotNull String> socialMedia;
	
	// TODO - Descobrir como tirar o _links do esquema que aparece no Swagger, ou, ao menos , definir um exemplo concreto

	
	public PerfilDTO() {
		this.ie = false;
		this.areas = new ArrayList<>();
		this.address = new LocalizacaoDTO();
		this.generalContact = new ContactDTO();
		this.socialMedia = new ArrayList<>();
	}
	
	
	public PerfilDTO(@NotBlank String name, @NotNull boolean ie, @NotNull @CNPJ String cnpj,
			@NotEmpty List<@NotBlank String> areas, String description, @NotNull @Valid LocalizacaoDTO address,
			@NotNull @Valid ContactDTO generalContact, @Valid ContactDTO applicationContact, @URL String website,
			List<@NotNull @URL String> socialMedia) {
		super();
		this.name = name;
		this.ie = ie;
		this.cnpj = cnpj;
		this.areas = areas;
		this.description = description;
		this.address = address;
		this.generalContact = generalContact;
		this.applicationContact = applicationContact;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public LocalizacaoDTO getAddress() {
		return address;
	}

	public void setAddress(LocalizacaoDTO address) {
		this.address = address;
	}

	public ContactDTO getGeneralContact() {
		return generalContact;
	}

	public void setGeneralContact(ContactDTO generalContact) {
		this.generalContact = generalContact;
	}

	public ContactDTO getApplicationContact() {
		return applicationContact;
	}

	public void setApplicationContact(ContactDTO applicationContact) {
		this.applicationContact = applicationContact;
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
		PerfilDTO other = (PerfilDTO) obj;
		return Objects.equals(address, other.address) && Objects.equals(applicationContact, other.applicationContact)
				&& Objects.equals(areas, other.areas) && Objects.equals(cnpj, other.cnpj)
				&& Objects.equals(description, other.description) && Objects.equals(generalContact, other.generalContact)
				&& ie == other.ie && Objects.equals(key, other.key) && Objects.equals(name, other.name)
				&& Objects.equals(socialMedia, other.socialMedia) && Objects.equals(website, other.website);
	}



}
