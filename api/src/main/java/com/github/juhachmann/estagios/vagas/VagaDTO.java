package com.github.juhachmann.estagios.vagas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.juhachmann.estagios.commom.ContactDTO;
import com.github.juhachmann.estagios.commom.LocalizacaoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Simple POJO to insert Vaga data
 * 
 */
@JsonPropertyOrder("id")
@Schema(name="Vaga")
public class VagaDTO extends RepresentationModel<VagaDTO> implements Serializable {

	// TODO - Como aceitar Html e outros na descrição? 

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
	private long key;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "NoBanks")
	private String owner;
	
	@Schema(example="Vaga de desenhista Junior" )
	@NotBlank
	private String title;
	
	@Schema(example="Vaga para desenhista etc etc et etc etc etc", description = "Aceita HTML" )
	@NotBlank
	private String description;
	
	@Schema(example="[\"Conhecimento em Adobe Photoshop\", \"Técnicas básicas de desenho digital\"]")
	private List<@NotBlank String> requirements;
	
	@Schema(example="[\"matutino\",\"vespertino\"]", allowableValues = "matutino,vespertino,noturno" )
	@NotEmpty
	private List<@NotBlank String> periods;
	
	@Schema(example="20",  description = "Carga horária semanal, em horas" )
	@NotNull
	@Min(1)
	private long workloadInHours;
	
	@Schema(example="900", description = "Remuneração da vaga" )
	@NotNull
	@Min(1)
	private long payment;
	
	@Schema(example="2024-01-22", description = "Data de início do estágio" )
	private LocalDate startsAt;
	
	@Schema(example="2024-06-30", description = "Data do final do estágio" )
	private LocalDate endsAt;
	
	@Schema(example="[\"medio\",\"tecnico\"]", allowableValues = "medio, tecnico, superior, pos", description = "Níveis de ensino que os/as candidatos/as à vaga podem estar cursando" )
	private List<@NotBlank String> levels;
	
	@Schema(example="[\"5\",\"6\"]", description = "Ids das Instituições de Ensino para as quais a vaga se destina. Se deixado em branco, a vaga será disponibilizada para todas as instituições de ensino com acesso ao sistema" )
	private List<@NotNull Long> ies;
	
	@Schema(example="[\"Design\",\"Artes Visuais\"]", description = "Áreas de estudo dos/as candidatos/as à vaga" )
	@NotEmpty
	private List<@NotBlank String> areas;
	
	@Schema(example="[\"Design\",\"Artes Visuais\"]", description = "Cursos específicos que o/a candidato/a pode estar cursando" )
	private List<@NotBlank String> courses;
	
	@Schema(description = "Contato para candidatura. Se deixado em branco, será considerado e exibido o contato definido no perfil da instituição ou empresa" )
	private ContactDTO contact;
	
	@Schema(description = "Endereço da vaga. Se deixado em branco, será considerado e exibido o contato definido no perfil da instituição ou empresa")
	private LocalizacaoDTO address;
	
	@Schema(example="[\"linkedin.com/minhaEmpresa/vaga012\"]", description = "Links externos que se relacionem com a vaga" )
	private List<@NotBlank String> externalLinks;
	
	@Schema(example="90", description = "Define por quantos dias a vaga ficará ativa no sistema. Se o valor não for definido, a vaga ficará disponível por 90 dias" )
	@Min(0)
	private int expiringInDays;
	
	@Schema(example="180", description = "Caso esta oferta de vaga de estágio seja recorrente na organização, é possível programar o intervalo de dias em que a vaga deve ser republicada no sistema" )
	@Min(0)
	private int renovateInDays;
	
	@Schema(example="InternalUser0123", description = "Id interno do criador da vaga. Útil para organizações em que o sistema será acessado por vários usuários internos e é preciso manter autorização de acesso às ofertas de vaga. Caso este valor seja fornecido, a vaga só poderá ser alterada ou excluída pelo mesmo usuário que a criou." )
	private String creatorInternalId;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example="2024-01-15")
	@JsonProperty("_createdAt")
	private Date createdAt;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, example="2024-01-17")
	@JsonProperty("_updatedAt")
	private Date updatedAt;
	
	
	public VagaDTO() {
		this.requirements = new ArrayList<>();
		this.periods = new ArrayList<>();
		this.levels = new ArrayList<>();
		this.ies = new ArrayList<>();
		this.areas = new ArrayList<>();
		this.courses = new ArrayList<>();
		this.contact = new ContactDTO();
		this.address = new LocalizacaoDTO();
		this.externalLinks = new ArrayList<>();
		this.expiringInDays = 90;
		this.renovateInDays = 0;
	}

	
	public VagaDTO(String owner, @NotBlank String title, @NotBlank String description,
			@NotEmpty List<@NotBlank String> requirements, @NotEmpty List<@NotBlank String> periods,
			@NotNull @Min(1) long workloadInHours, @NotNull @Min(1) long payment, @FutureOrPresent LocalDate startsAt,
			@FutureOrPresent LocalDate endsAt, @NotNull List<@NotBlank String> levels, List<@NotNull Long> ies,
			@NotEmpty List<@NotBlank String> areas, List<@NotBlank String> courses, ContactDTO contact,
			LocalizacaoDTO address, List<@NotBlank String> externalLinks, @Min(0) int expiringInDays,
			@Min(0) int renovateInDays, String creatorInternalId) {
		super();
		this.owner = owner;
		this.title = title;
		this.description = description;
		this.requirements = requirements;
		this.periods = periods;
		this.workloadInHours = workloadInHours;
		this.payment = payment;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.levels = levels;
		this.ies = ies;
		this.areas = areas;
		this.courses = courses;
		this.contact = contact;
		this.address = address;
		this.externalLinks = externalLinks;
		this.expiringInDays = expiringInDays;
		this.renovateInDays = renovateInDays;
		this.creatorInternalId = creatorInternalId;
	}


	public long getKey() {
		return key;
	}


	public void setKey(long key) {
		this.key = key;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<String> getRequirements() {
		return requirements;
	}


	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}


	public List<String> getPeriods() {
		return periods;
	}


	public void setPeriods(List<String> periods) {
		this.periods = periods;
	}


	public long getWorkloadInHours() {
		return workloadInHours;
	}


	public void setWorkloadInHours(long workloadInHours) {
		this.workloadInHours = workloadInHours;
	}


	public long getPayment() {
		return payment;
	}


	public void setPayment(long payment) {
		this.payment = payment;
	}


	public LocalDate getStartsAt() {
		return startsAt;
	}


	public void setStartsAt(LocalDate startsAt) {
		this.startsAt = startsAt;
	}


	public LocalDate getEndsAt() {
		return endsAt;
	}


	public void setEndsAt(LocalDate endsAt) {
		this.endsAt = endsAt;
	}


	public List<String> getLevels() {
		return levels;
	}


	public void setLevels(List<String> levels) {
		this.levels = levels;
	}


	public List<Long> getIes() {
		return ies;
	}


	public void setIes(List<Long> ies) {
		this.ies = ies;
	}


	public List<String> getAreas() {
		return areas;
	}


	public void setAreas(List<String> areas) {
		this.areas = areas;
	}


	public List<String> getCourses() {
		return courses;
	}


	public void setCourses(List<String> courses) {
		this.courses = courses;
	}


	public ContactDTO getContact() {
		return contact;
	}


	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}


	public LocalizacaoDTO getAddress() {
		return address;
	}


	public void setAddress(LocalizacaoDTO address) {
		this.address = address;
	}


	public List<String> getExternalLinks() {
		return externalLinks;
	}


	public void setExternalLinks(List<String> externalLinks) {
		this.externalLinks = externalLinks;
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


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
		VagaDTO other = (VagaDTO) obj;
		return Objects.equals(address, other.address) && Objects.equals(areas, other.areas)
				&& Objects.equals(contact, other.contact) && Objects.equals(courses, other.courses)
				&& Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(creatorInternalId, other.creatorInternalId)
				&& Objects.equals(description, other.description) && Objects.equals(endsAt, other.endsAt)
				&& expiringInDays == other.expiringInDays && Objects.equals(externalLinks, other.externalLinks)
				&& Objects.equals(ies, other.ies) && key == other.key && Objects.equals(levels, other.levels)
				&& Objects.equals(owner, other.owner) && payment == other.payment
				&& Objects.equals(periods, other.periods) && renovateInDays == other.renovateInDays
				&& Objects.equals(requirements, other.requirements) && Objects.equals(startsAt, other.startsAt)
				&& Objects.equals(title, other.title) && Objects.equals(updatedAt, other.updatedAt)
				&& workloadInHours == other.workloadInHours;
	}
	
	
	
	

}
