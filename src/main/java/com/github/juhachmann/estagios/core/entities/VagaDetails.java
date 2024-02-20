package com.github.juhachmann.estagios.core.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Se não tiver lógica nenhuma aqui,não precisa sem implementar, só deixar o abstrato mesmo

public class VagaDetails {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotEmpty
    private List<@NotBlank String> requisites = new ArrayList<>();

    @NotEmpty
    private List<@NotBlank String> areas = new ArrayList<>();

    @NotEmpty
    private List<@NotBlank String> periods = new ArrayList<>();

    @NotEmpty
    private List<@NotBlank String> levels = new ArrayList<>();

    private LocalDateTime startsAt;

    private Integer durationInMonths;

    @NotNull
    @Min(0)
    private Double remuneration;

    @NotNull
    @Min(0)
    private Integer weekWorkload;

    @NotBlank
    private String format;

    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;



}
