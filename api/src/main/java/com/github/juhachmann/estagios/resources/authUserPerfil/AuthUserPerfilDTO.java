package com.github.juhachmann.estagios.perfil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Schema(name="Perfil Privado", description = "Perfil privado da instituição ou empresa")
@JsonPropertyOrder("id")
@Validated
public class PerfilPrivadoDTO extends PerfilDTO implements Serializable { // TODO Dúvida: não consegui estender do PerfilPublico pq tive que estender do RepresentationModel<> e estava dando erro lá na frente.. tem algum jeito de consertar isso?
	
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "CNPJ válido da instituição ou empresa", requiredMode = Schema.RequiredMode.REQUIRED, example="18009962000177")
	@NotNull
	@CNPJ
	private String cnpj;
	
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
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY )
	@JsonProperty("_createdAt")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("_updatedAt")
	private LocalDateTime updatedAt;

	
	public PerfilPrivadoDTO() {
		super();
		this.address = new LocalizacaoDTO();
		this.generalContact = new ContactDTO();
		this.applicationContact = new ContactDTO();
	}


	public PerfilPrivadoDTO(Long key, @NotBlank String name, @NotNull boolean ie,
			@NotEmpty List<@NotBlank String> areas, String description, String website,
			List<@NotNull String> socialMedia, @NotNull @CNPJ String cnpj, @NotNull @Valid LocalizacaoDTO address,
			@NotNull @Valid ContactDTO generalContact, @Valid ContactDTO applicationContact, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super(key, name, ie, areas, description, website, socialMedia);
		this.cnpj = cnpj;
		this.address = address;
		this.generalContact = generalContact;
		this.applicationContact = applicationContact;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public int hashCode() {
		int result = super.hashCode();
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
		PerfilPrivadoDTO other = (PerfilPrivadoDTO) obj;
		return Objects.equals(address, other.address) && Objects.equals(applicationContact, other.applicationContact)
				&& Objects.equals(cnpj, other.cnpj) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(generalContact, other.generalContact) && Objects.equals(updatedAt, other.updatedAt);
	}


}
