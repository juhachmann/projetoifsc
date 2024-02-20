package com.github.juhachmann.estagios.core.application;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.github.juhachmann.estagios.core.entities.User}
 */
public class UserPrivateProfileDTO implements Serializable {
    private static final long serialVersionUID = 8202278956962107689L;
    private Long id;
    private boolean ie;
    private String profileName;
    private String profileCnpj;
    private String profileDescription;
    private String profileMainPhone;
    private String profileMainEmail;
    private String profileOtherPhone;
    private String profileOtherEmail;
    private String profileAddressLine;
    private String profileCity;
    private String profileState;
    private String profileCountry;
    private Date profileCreated_at;
    private Date profileUpdated_at;

    public UserPrivateProfileDTO() {
    }

    public UserPrivateProfileDTO(Long id, boolean ie, String profileName, String profileCnpj, String profileDescription, String profileMainPhone, String profileMainEmail, String profileOtherPhone, String profileOtherEmail, String profileAddressLine, String profileCity, String profileState, String profileCountry, Date profileCreated_at, Date profileUpdated_at) {
        this.id = id;
        this.ie = ie;
        this.profileName = profileName;
        this.profileCnpj = profileCnpj;
        this.profileDescription = profileDescription;
        this.profileMainPhone = profileMainPhone;
        this.profileMainEmail = profileMainEmail;
        this.profileOtherPhone = profileOtherPhone;
        this.profileOtherEmail = profileOtherEmail;
        this.profileAddressLine = profileAddressLine;
        this.profileCity = profileCity;
        this.profileState = profileState;
        this.profileCountry = profileCountry;
        this.profileCreated_at = profileCreated_at;
        this.profileUpdated_at = profileUpdated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileCnpj() {
        return profileCnpj;
    }

    public void setProfileCnpj(String profileCnpj) {
        this.profileCnpj = profileCnpj;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getProfileMainPhone() {
        return profileMainPhone;
    }

    public void setProfileMainPhone(String profileMainPhone) {
        this.profileMainPhone = profileMainPhone;
    }

    public String getProfileMainEmail() {
        return profileMainEmail;
    }

    public void setProfileMainEmail(String profileMainEmail) {
        this.profileMainEmail = profileMainEmail;
    }

    public String getProfileOtherPhone() {
        return profileOtherPhone;
    }

    public void setProfileOtherPhone(String profileOtherPhone) {
        this.profileOtherPhone = profileOtherPhone;
    }

    public String getProfileOtherEmail() {
        return profileOtherEmail;
    }

    public void setProfileOtherEmail(String profileOtherEmail) {
        this.profileOtherEmail = profileOtherEmail;
    }

    public String getProfileAddressLine() {
        return profileAddressLine;
    }

    public void setProfileAddressLine(String profileAddressLine) {
        this.profileAddressLine = profileAddressLine;
    }

    public String getProfileCity() {
        return profileCity;
    }

    public void setProfileCity(String profileCity) {
        this.profileCity = profileCity;
    }

    public String getProfileState() {
        return profileState;
    }

    public void setProfileState(String profileState) {
        this.profileState = profileState;
    }

    public String getProfileCountry() {
        return profileCountry;
    }

    public void setProfileCountry(String profileCountry) {
        this.profileCountry = profileCountry;
    }

    public Date getProfileCreated_at() {
        return profileCreated_at;
    }

    public void setProfileCreated_at(Date profileCreated_at) {
        this.profileCreated_at = profileCreated_at;
    }

    public Date getProfileUpdated_at() {
        return profileUpdated_at;
    }

    public void setProfileUpdated_at(Date profileUpdated_at) {
        this.profileUpdated_at = profileUpdated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrivateProfileDTO entity = (UserPrivateProfileDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ie, entity.ie) &&
                Objects.equals(this.profileName, entity.profileName) &&
                Objects.equals(this.profileCnpj, entity.profileCnpj) &&
                Objects.equals(this.profileDescription, entity.profileDescription) &&
                Objects.equals(this.profileMainPhone, entity.profileMainPhone) &&
                Objects.equals(this.profileMainEmail, entity.profileMainEmail) &&
                Objects.equals(this.profileOtherPhone, entity.profileOtherPhone) &&
                Objects.equals(this.profileOtherEmail, entity.profileOtherEmail) &&
                Objects.equals(this.profileAddressLine, entity.profileAddressLine) &&
                Objects.equals(this.profileCity, entity.profileCity) &&
                Objects.equals(this.profileState, entity.profileState) &&
                Objects.equals(this.profileCountry, entity.profileCountry) &&
                Objects.equals(this.profileCreated_at, entity.profileCreated_at) &&
                Objects.equals(this.profileUpdated_at, entity.profileUpdated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ie, profileName, profileCnpj, profileDescription, profileMainPhone, profileMainEmail, profileOtherPhone, profileOtherEmail, profileAddressLine, profileCity, profileState, profileCountry, profileCreated_at, profileUpdated_at);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "ie = " + ie + ", " +
                "profileName = " + profileName + ", " +
                "profileCnpj = " + profileCnpj + ", " +
                "profileDescription = " + profileDescription + ", " +
                "profileMainPhone = " + profileMainPhone + ", " +
                "profileMainEmail = " + profileMainEmail + ", " +
                "profileOtherPhone = " + profileOtherPhone + ", " +
                "profileOtherEmail = " + profileOtherEmail + ", " +
                "profileAddressLine = " + profileAddressLine + ", " +
                "profileCity = " + profileCity + ", " +
                "profileState = " + profileState + ", " +
                "profileCountry = " + profileCountry + ", " +
                "profileCreated_at = " + profileCreated_at + ", " +
                "profileUpdated_at = " + profileUpdated_at + ")";
    }
}