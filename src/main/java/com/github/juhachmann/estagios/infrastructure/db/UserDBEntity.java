package com.github.juhachmann.estagios.infrastructure.db;

import com.github.juhachmann.estagios.core.entities.UserInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Cacheable
@Validated

public class UserDBEntity implements UserInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @CNPJ
    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private boolean ies;

    @NotBlank
    @Column(length = 1000, nullable = false)
    private String description;

    @NotBlank
    @Column(nullable = false)
    private String mainPhone;

    @Email
    @Column(nullable = false)
    private String mainEmail;

    private String otherPhone;

    @Email
    private String otherEmail;

    private String addressLine;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @NotBlank
    @Column(nullable = false)
    private String state;

    @NotBlank
    @Column(nullable = false)
    private String country;


    // TODO : FIX : Created_at e Updated_At
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    // Só chamou quando foi solicitado, por meio do get!
    @OneToMany(mappedBy = "owner")
    @OrderBy("id DESC")
    private List<VagaDBEntity> ownedVagas;

    // Só chama quando é solicitado, por meio do get!
    @ManyToMany
    @JoinTable(
            name = "ies_vagas",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "vaga_id", nullable = false))
    @OrderBy("id")
    private List<VagaDBEntity> receivedVagas;

    public UserDBEntity() {
    }


    public UserDBEntity (
            String name, String cnpj, boolean ies, String description,
            String mainPhone, String mainEmail, String city, String state, String country
    ) {

        this.name = name;
        this.cnpj = cnpj;
        this.ies = ies;
        this.description = description;
        this.mainPhone = mainPhone;
        this.mainEmail = mainEmail;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public static UserDBEntity createUser (String name, String cnpj, boolean ies, String description, String mainPhone, String mainEmail, String city, String state, String country ) {
        return new UserDBEntity(name, cnpj, ies, description, mainPhone, mainEmail, city, state, country);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public boolean isIes() {
        return ies;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getMainPhone() {
        return mainPhone;
    }

    @Override
    public String getMainEmail() {
        return mainEmail;
    }

    @Override
    public String getOtherPhone() {
        return otherPhone;
    }

    @Override
    public String getOtherEmail() {
        return otherEmail;
    }

    @Override
    public String getAddressLine() {
        return addressLine;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public Date getCreated_at() {
        return created_at;
    }

    @Override
    public Date getUpdated_at() {
        return updated_at;
    }

    public List<VagaDBEntity> getOwnedVagas() {
        return ownedVagas;
    }

    public List<VagaDBEntity> getReceivedVagas() {
        return receivedVagas;
    }




}
