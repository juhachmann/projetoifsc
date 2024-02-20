package com.github.juhachmann.estagios.infrastructure.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "vaga")
@Table(name = "vagas")
@Cacheable
@Validated

public class VagaDBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserDBEntity owner;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String description;

    private String requisites;

    @ManyToMany
    @JoinTable(
            name = "areas_vagas",
            joinColumns = @JoinColumn(name = "vaga_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "area_id", nullable = false))
    @OrderBy("name")
    private List<AreaDBEntity> areas;

    @ManyToMany
    @JoinTable(
            name = "periods_vagas",
            joinColumns = @JoinColumn(name = "vaga_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "period_id", nullable = false))
    @OrderBy("id")
    private List<PeriodDBEntity> periods;

    @ManyToMany
    @JoinTable(
            name = "levels_vagas",
            joinColumns = @JoinColumn(name = "vaga_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "level_id"))
    @OrderBy("order")
    private List<LevelDBEntity> levels;

    @Min(0)
    @Column(nullable = false)
    private double remuneration;

    @Min(0)
    @Column(nullable = false)
    private int weeklyWorkload;

    // TODO como pegar os valores PRESENCIAL, HOME OFFICE, HÍBRIDO
    @Column(nullable = false)
    private String format;

    private String addressLine;
    private String city;
    private String state;
    private String country;

    private String phone;
    @Email
    private String email;

    @Min(0)
    @Column(nullable = false)
    private int renewWithinDays;

    @Column(nullable = false)
    private LocalDateTime expires_at;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private LocalDateTime updated_at;

    @ManyToMany(mappedBy = "receivedVagas")
    private List<UserDBEntity> receivers;


    public VagaDBEntity() {
        this.receivers = new ArrayList<>();
        this.periods = new ArrayList<>();
        this.areas = new ArrayList<>();
        this.levels = new ArrayList<>();
    }

    public VagaDBEntity(UserDBEntity owner, String title, String description, String requisites, double remuneration, int weeklyWorkload, String format, String addressLine, String city, String state, String country, String phone, String email, int renewWithinDays, LocalDateTime expires_at) {
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.requisites = requisites;
        this.remuneration = remuneration;
        this.weeklyWorkload = weeklyWorkload;
        this.format = format;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.renewWithinDays = renewWithinDays;
        this.expires_at = expires_at;
    }


    public Long getId() {
        return id;
    }

    public UserDBEntity getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRequisites() {
        return requisites;
    }

    public List<AreaDBEntity> getAreas() {
        return areas;
    }

    public List<PeriodDBEntity> getPeriods() {
        return periods;
    }

    public List<LevelDBEntity> getLevels() {
        return levels;
    }

    public double getRemuneration() {
        return remuneration;
    }

    public int getWeeklyWorkload() {
        return weeklyWorkload;
    }

    public String getFormat() {
        return format;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getRenewWithinDays() {
        return renewWithinDays;
    }

    public LocalDateTime getExpires_at() {
        return expires_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public List<UserDBEntity> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<UserDBEntity> receivers) {
        this.receivers = receivers;
    }
}
