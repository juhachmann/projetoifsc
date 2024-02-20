package com.github.juhachmann.estagios.core.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Date;

// Estas classes : se não tiver nenhuma lógica, não precisa implementar
// Deixa só na interface

public class UserProfile {

    @NotBlank
    private String name;

    @CNPJ
    private String cnpj;

    @NotBlank
    private String description;

    @NotBlank
    private String mainPhone;

    @Email
    private String mainEmail;

    private String otherPhone;

    @Email
    private String otherEmail;

    private String addressLine;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    private Date created_at;

    private Date updated_at;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
