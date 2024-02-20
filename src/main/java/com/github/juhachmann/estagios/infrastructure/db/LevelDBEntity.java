package com.github.juhachmann.estagios.infrastructure.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Entity(name = "level")
@Table(name = "levels")
@Validated
public class LevelDBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Min(0)
    @Column(nullable = false)
    private int order;

    @NotBlank
    @Column(nullable = false)
    private String name;


}
