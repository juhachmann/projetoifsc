package com.github.juhachmann.estagios.core.application;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.github.juhachmann.estagios.core.entities.Vaga}
 */
public class VagaPrivateSettingsDTO implements Serializable {
    private static final long serialVersionUID = -4421943209519960114L;
    private Long id;
    private List<String> messages;
    private List<Long> receiverIds;
    private String detailsTitle;
    private String detailsDescription;
    private List<String> detailsRequisites;
    private List<String> detailsAreas;
    private List<String> detailsPeriods;
    private List<String> detailsLevels;
    private LocalDateTime detailsStartsAt;
    private Integer detailsDurationInMonths;
    private Double detailsRemuneration;
    private Integer detailsWeekWorkload;
    private String detailsFormat;
    private String detailsAddressLine;
    private String detailsCity;
    private String detailsState;
    private String detailsCountry;
    private String detailsPhone;
    private String detailsEmail;
    private LocalDateTime detailsCreated_at;
    private LocalDateTime detailsUpdated_at;
    private Integer settingsExpiresInDays = 0;
    private Integer settingsRenovateInFays = 0;

    public VagaPrivateSettingsDTO() {
    }

    public VagaPrivateSettingsDTO(Long id, List<String> messages, List<Long> receiverIds, String detailsTitle, String detailsDescription, List<String> detailsRequisites, List<String> detailsAreas, List<String> detailsPeriods, List<String> detailsLevels, LocalDateTime detailsStartsAt, Integer detailsDurationInMonths, Double detailsRemuneration, Integer detailsWeekWorkload, String detailsFormat, String detailsAddressLine, String detailsCity, String detailsState, String detailsCountry, String detailsPhone, String detailsEmail, LocalDateTime detailsCreated_at, LocalDateTime detailsUpdated_at, Integer settingsExpiresInDays, Integer settingsRenovateInFays) {
        this.id = id;
        this.messages = messages;
        this.receiverIds = receiverIds;
        this.detailsTitle = detailsTitle;
        this.detailsDescription = detailsDescription;
        this.detailsRequisites = detailsRequisites;
        this.detailsAreas = detailsAreas;
        this.detailsPeriods = detailsPeriods;
        this.detailsLevels = detailsLevels;
        this.detailsStartsAt = detailsStartsAt;
        this.detailsDurationInMonths = detailsDurationInMonths;
        this.detailsRemuneration = detailsRemuneration;
        this.detailsWeekWorkload = detailsWeekWorkload;
        this.detailsFormat = detailsFormat;
        this.detailsAddressLine = detailsAddressLine;
        this.detailsCity = detailsCity;
        this.detailsState = detailsState;
        this.detailsCountry = detailsCountry;
        this.detailsPhone = detailsPhone;
        this.detailsEmail = detailsEmail;
        this.detailsCreated_at = detailsCreated_at;
        this.detailsUpdated_at = detailsUpdated_at;
        this.settingsExpiresInDays = settingsExpiresInDays;
        this.settingsRenovateInFays = settingsRenovateInFays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<Long> getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(List<Long> receiverIds) {
        this.receiverIds = receiverIds;
    }

    public String getDetailsTitle() {
        return detailsTitle;
    }

    public void setDetailsTitle(String detailsTitle) {
        this.detailsTitle = detailsTitle;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    public List<String> getDetailsRequisites() {
        return detailsRequisites;
    }

    public void setDetailsRequisites(List<String> detailsRequisites) {
        this.detailsRequisites = detailsRequisites;
    }

    public List<String> getDetailsAreas() {
        return detailsAreas;
    }

    public void setDetailsAreas(List<String> detailsAreas) {
        this.detailsAreas = detailsAreas;
    }

    public List<String> getDetailsPeriods() {
        return detailsPeriods;
    }

    public void setDetailsPeriods(List<String> detailsPeriods) {
        this.detailsPeriods = detailsPeriods;
    }

    public List<String> getDetailsLevels() {
        return detailsLevels;
    }

    public void setDetailsLevels(List<String> detailsLevels) {
        this.detailsLevels = detailsLevels;
    }

    public LocalDateTime getDetailsStartsAt() {
        return detailsStartsAt;
    }

    public void setDetailsStartsAt(LocalDateTime detailsStartsAt) {
        this.detailsStartsAt = detailsStartsAt;
    }

    public Integer getDetailsDurationInMonths() {
        return detailsDurationInMonths;
    }

    public void setDetailsDurationInMonths(Integer detailsDurationInMonths) {
        this.detailsDurationInMonths = detailsDurationInMonths;
    }

    public Double getDetailsRemuneration() {
        return detailsRemuneration;
    }

    public void setDetailsRemuneration(Double detailsRemuneration) {
        this.detailsRemuneration = detailsRemuneration;
    }

    public Integer getDetailsWeekWorkload() {
        return detailsWeekWorkload;
    }

    public void setDetailsWeekWorkload(Integer detailsWeekWorkload) {
        this.detailsWeekWorkload = detailsWeekWorkload;
    }

    public String getDetailsFormat() {
        return detailsFormat;
    }

    public void setDetailsFormat(String detailsFormat) {
        this.detailsFormat = detailsFormat;
    }

    public String getDetailsAddressLine() {
        return detailsAddressLine;
    }

    public void setDetailsAddressLine(String detailsAddressLine) {
        this.detailsAddressLine = detailsAddressLine;
    }

    public String getDetailsCity() {
        return detailsCity;
    }

    public void setDetailsCity(String detailsCity) {
        this.detailsCity = detailsCity;
    }

    public String getDetailsState() {
        return detailsState;
    }

    public void setDetailsState(String detailsState) {
        this.detailsState = detailsState;
    }

    public String getDetailsCountry() {
        return detailsCountry;
    }

    public void setDetailsCountry(String detailsCountry) {
        this.detailsCountry = detailsCountry;
    }

    public String getDetailsPhone() {
        return detailsPhone;
    }

    public void setDetailsPhone(String detailsPhone) {
        this.detailsPhone = detailsPhone;
    }

    public String getDetailsEmail() {
        return detailsEmail;
    }

    public void setDetailsEmail(String detailsEmail) {
        this.detailsEmail = detailsEmail;
    }

    public LocalDateTime getDetailsCreated_at() {
        return detailsCreated_at;
    }

    public void setDetailsCreated_at(LocalDateTime detailsCreated_at) {
        this.detailsCreated_at = detailsCreated_at;
    }

    public LocalDateTime getDetailsUpdated_at() {
        return detailsUpdated_at;
    }

    public void setDetailsUpdated_at(LocalDateTime detailsUpdated_at) {
        this.detailsUpdated_at = detailsUpdated_at;
    }

    public Integer getSettingsExpiresInDays() {
        return settingsExpiresInDays;
    }

    public void setSettingsExpiresInDays(Integer settingsExpiresInDays) {
        this.settingsExpiresInDays = settingsExpiresInDays;
    }

    public Integer getSettingsRenovateInFays() {
        return settingsRenovateInFays;
    }

    public void setSettingsRenovateInFays(Integer settingsRenovateInFays) {
        this.settingsRenovateInFays = settingsRenovateInFays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VagaPrivateSettingsDTO entity = (VagaPrivateSettingsDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.messages, entity.messages) &&
                Objects.equals(this.receiverIds, entity.receiverIds) &&
                Objects.equals(this.detailsTitle, entity.detailsTitle) &&
                Objects.equals(this.detailsDescription, entity.detailsDescription) &&
                Objects.equals(this.detailsRequisites, entity.detailsRequisites) &&
                Objects.equals(this.detailsAreas, entity.detailsAreas) &&
                Objects.equals(this.detailsPeriods, entity.detailsPeriods) &&
                Objects.equals(this.detailsLevels, entity.detailsLevels) &&
                Objects.equals(this.detailsStartsAt, entity.detailsStartsAt) &&
                Objects.equals(this.detailsDurationInMonths, entity.detailsDurationInMonths) &&
                Objects.equals(this.detailsRemuneration, entity.detailsRemuneration) &&
                Objects.equals(this.detailsWeekWorkload, entity.detailsWeekWorkload) &&
                Objects.equals(this.detailsFormat, entity.detailsFormat) &&
                Objects.equals(this.detailsAddressLine, entity.detailsAddressLine) &&
                Objects.equals(this.detailsCity, entity.detailsCity) &&
                Objects.equals(this.detailsState, entity.detailsState) &&
                Objects.equals(this.detailsCountry, entity.detailsCountry) &&
                Objects.equals(this.detailsPhone, entity.detailsPhone) &&
                Objects.equals(this.detailsEmail, entity.detailsEmail) &&
                Objects.equals(this.detailsCreated_at, entity.detailsCreated_at) &&
                Objects.equals(this.detailsUpdated_at, entity.detailsUpdated_at) &&
                Objects.equals(this.settingsExpiresInDays, entity.settingsExpiresInDays) &&
                Objects.equals(this.settingsRenovateInFays, entity.settingsRenovateInFays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messages, receiverIds, detailsTitle, detailsDescription, detailsRequisites, detailsAreas, detailsPeriods, detailsLevels, detailsStartsAt, detailsDurationInMonths, detailsRemuneration, detailsWeekWorkload, detailsFormat, detailsAddressLine, detailsCity, detailsState, detailsCountry, detailsPhone, detailsEmail, detailsCreated_at, detailsUpdated_at, settingsExpiresInDays, settingsRenovateInFays);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "messages = " + messages + ", " +
                "receiverIds = " + receiverIds + ", " +
                "detailsTitle = " + detailsTitle + ", " +
                "detailsDescription = " + detailsDescription + ", " +
                "detailsRequisites = " + detailsRequisites + ", " +
                "detailsAreas = " + detailsAreas + ", " +
                "detailsPeriods = " + detailsPeriods + ", " +
                "detailsLevels = " + detailsLevels + ", " +
                "detailsStartsAt = " + detailsStartsAt + ", " +
                "detailsDurationInMonths = " + detailsDurationInMonths + ", " +
                "detailsRemuneration = " + detailsRemuneration + ", " +
                "detailsWeekWorkload = " + detailsWeekWorkload + ", " +
                "detailsFormat = " + detailsFormat + ", " +
                "detailsAddressLine = " + detailsAddressLine + ", " +
                "detailsCity = " + detailsCity + ", " +
                "detailsState = " + detailsState + ", " +
                "detailsCountry = " + detailsCountry + ", " +
                "detailsPhone = " + detailsPhone + ", " +
                "detailsEmail = " + detailsEmail + ", " +
                "detailsCreated_at = " + detailsCreated_at + ", " +
                "detailsUpdated_at = " + detailsUpdated_at + ", " +
                "settingsExpiresInDays = " + settingsExpiresInDays + ", " +
                "settingsRenovateInFays = " + settingsRenovateInFays + ")";
    }
}