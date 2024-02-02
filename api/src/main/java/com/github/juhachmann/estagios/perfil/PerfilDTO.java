package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.juhachmann.estagios.commom.ContactDTO;
import com.github.juhachmann.estagios.commom.LocalizacaoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder("id")
@Validated
public class PerfilDTO extends RepresentationModel<PerfilDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;
	
	@NotBlank
	private String name;
	
	@NotNull
	private boolean ie;

	@NotNull
	@CNPJ
	private String cnpj;
	
	@NotNull
	@Valid
	private LocalizacaoDTO address;
	
	@NotNull
	@Valid
	private ContactDTO generalContact;

	@Valid
	private ContactDTO applicationContact;
	
	@URL
	private String website;
	
	private List<@NotNull @URL String> socialMedia;
	
	@NotEmpty
	private List<@NotBlank String> areas;
	
	private String extraInfo;
	
//	@NotNull
//	@Valid
//	private List<ConfigDTO> configs;
	
	
//	private enum CategoriaEnum {
//		IE("ie"),
//		EMPRESA("empresa");
//
//	    private String value;
//
//	    CategoriaEnum(String value) {
//	      this.value = value;
//	    }
//		
//	    @Override
//	    @JsonValue
//	    public String toString() {
//	      return String.valueOf(value);
//	    }
//
//	    @JsonCreator
//	    public static CategoriaEnum fromValue(String text) {
//	      for (CategoriaEnum b : CategoriaEnum.values()) {
//	        if (String.valueOf(b.value).equals(text)) {
//	          return b;
//	        }
//	      }
//	      return null;
//	    }
//	}

	public PerfilDTO() {
		super();
		this.ie = false;
		this.address = new LocalizacaoDTO();
		this.generalContact = new ContactDTO();
		this.socialMedia = new ArrayList<>();
		this.areas =  new ArrayList<>();	
	}

	
	public PerfilDTO(Long key, @NotBlank String name, @NotNull boolean ie, @NotBlank String cnpj,
			@NotNull @Valid LocalizacaoDTO address, @NotNull @Valid ContactDTO generalContact,
			@Valid ContactDTO applicationContact, String website, List<String> socialMedia,
			@NotNull List<@NotBlank String> areas, String extraInfo
			//,  @NotNull @Valid List<ConfigDTO> configs
			) {
		super();
		this.key = key;
		this.name = name;
		this.ie = ie;
		this.cnpj = cnpj;
		this.address = address;
		this.generalContact = generalContact;
		this.applicationContact = applicationContact;
		this.website = website;
		this.socialMedia = socialMedia;
		this.areas = areas;
		this.extraInfo = extraInfo;
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


//	public List<ConfigDTO> getConfigs() {
//		return configs;
//	}
//
//
//	public void setConfigs(List<ConfigDTO> configs) {
//		this.configs = configs;
//	}


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
				&& Objects.equals(areas, other.areas) && Objects.equals(ie, other.ie)
				&& Objects.equals(cnpj, other.cnpj) 
				//&& Objects.equals(configs, other.configs)
				&& Objects.equals(extraInfo, other.extraInfo) && Objects.equals(generalContact, other.generalContact)
				&& Objects.equals(key, other.key) && Objects.equals(name, other.name)
				&& Objects.equals(socialMedia, other.socialMedia) && Objects.equals(website, other.website);
	}

}
