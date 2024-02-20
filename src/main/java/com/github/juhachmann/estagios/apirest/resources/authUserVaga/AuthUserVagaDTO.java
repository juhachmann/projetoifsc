package com.github.juhachmann.estagios.apirest.resources.authUserVaga;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.juhachmann.estagios.apirest.resources.shared.ContactDTO;
import com.github.juhachmann.estagios.apirest.resources.shared.LocalizacaoDTO;
import com.github.juhachmann.estagios.apirest.resources.vagas.VagaDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.github.juhachmann.estagios.apirest.resources.authUserVaga.AuthUserVaga}
 */
public class AuthUserVagaDTO extends VagaDTO {

	private static final long serialVersionUID = 1L;
	
	// TODO - Como aceitar Html e outros na descrição? 
	
	@Schema(example="[5, 6]", description = "Ids das Instituições de Ensino para as quais a vaga se destina. Se deixado em branco, a vaga será disponibilizada para todas as instituições de ensino com acesso ao sistema" )
	private List<@NotNull Long> ies;
	
	@Schema(example="90", accessMode = Schema.AccessMode.WRITE_ONLY, description = "Define por quantos dias a vaga ficará ativa no sistema. Se o valor não for definido, a vaga ficará disponível por 90 dias" )
	@Min(0)
	private int expiringInDays;
		
	@Schema(example="180", description = "Caso esta oferta de vaga de estágio seja recorrente na organização, é possível programar o intervalo de dias em que a vaga deve ser republicada no sistema" )
	@Min(0)
	private int renovateInDays;
	
	@Schema(example="InternalUser0123", description = "Id interno do criador da vaga. Útil para organizações em que o sistema será acessado por vários usuários internos e é preciso manter autorização de acesso às ofertas de vaga. Caso este valor seja fornecido, a vaga só poderá ser alterada ou excluída pelo mesmo usuário que a criou." )
	private String creatorInternalId;

	
	public AuthUserVagaDTO() {
		super();
		this.ies = new ArrayList<>();
		this.expiringInDays = 90;
		if (super.getExpiresAt() == null) {
			super.setExpiresAt( super.getCreatedAt().plusDays(expiringInDays).toLocalDate() );
		}
	}


	public AuthUserVagaDTO(long key, String owner, Long ownerId, @NotBlank String title, @NotBlank String description,
			List<@NotBlank String> requirements, @NotEmpty List<@NotBlank String> periods,
			@NotNull @Min(1) long workloadInHours, @NotNull @Min(1) long payment, LocalDate startsAt, LocalDate endsAt,
			List<@NotBlank String> levels, @NotEmpty List<@NotBlank String> areas, List<@NotBlank String> courses,
			ContactDTO contact, LocalizacaoDTO address, List<@NotBlank String> externalLinks,
			@NotNull @FutureOrPresent LocalDate expiresAt, LocalDateTime createdAt, LocalDateTime updatedAt, 
			List<@NotNull Long> ies, @Min(0) int expiringInDays, @Min(0) int renovateInDays,
			String creatorInternalId) {
		super(key, owner, ownerId, title, description, requirements, periods, workloadInHours, payment, startsAt, endsAt,
				levels, areas, courses, contact, address, externalLinks, expiresAt, createdAt, updatedAt);
		this.ies = ies;
		this.expiringInDays = expiringInDays;
		this.renovateInDays = renovateInDays;
		this.creatorInternalId = creatorInternalId;
	}


	public List<Long> getIes() {
		return ies;
	}


	public void setIes(List<Long> ies) {
		this.ies = ies;
	}


	public int getExpiringInDays() {
		return expiringInDays;
	}


	public void setExpiringInDays(int expiringInDays) {
		this.expiringInDays = expiringInDays;
	}


	public int getRenovateInDays() {
		return renovateInDays;
	}


	public void setRenovateInDays(int renovateInDays) {
		this.renovateInDays = renovateInDays;
	}


	public String getCreatorInternalId() {
		return creatorInternalId;
	}


	public void setCreatorInternalId(String creatorInternalId) {
		this.creatorInternalId = creatorInternalId;
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
		AuthUserVagaDTO other = (AuthUserVagaDTO) obj;
		return Objects.equals(creatorInternalId, other.creatorInternalId) && expiringInDays == other.expiringInDays
				&& Objects.equals(ies, other.ies) && renovateInDays == other.renovateInDays;
	}
	
	
	

}
