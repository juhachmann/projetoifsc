package com.github.juhachmann.estagios.core.application;

/**
 * DTO for {@link com.github.juhachmann.estagios.core.entities.User}
 */
public class UserPublicProfileDTO {

    Long id;
    boolean ie;
    String profileName;
    String profileCnpj;
    String profileDescription;
    String profileMainPhone;
    String profileMainEmail;
    String profileAddressLine;
    String profileCity;
    String profileState;
    String profileCountry;

    public UserPublicProfileDTO() {
    }

    public UserPublicProfileDTO(Long id, boolean ie, String profileName, String profileCnpj, String profileDescription, String profileMainPhone, String profileMainEmail, String profileAddressLine, String profileCity, String profileState, String profileCountry) {
        this.id = id;
        this.ie = ie;
        this.profileName = profileName;
        this.profileCnpj = profileCnpj;
        this.profileDescription = profileDescription;
        this.profileMainPhone = profileMainPhone;
        this.profileMainEmail = profileMainEmail;
        this.profileAddressLine = profileAddressLine;
        this.profileCity = profileCity;
        this.profileState = profileState;
        this.profileCountry = profileCountry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIe() {
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

}