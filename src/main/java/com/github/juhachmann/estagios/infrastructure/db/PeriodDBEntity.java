package com.github.juhachmann.estagios.infrastructure.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Entity(name = "period")
@Table(name = "periods")
@Validated
public class PeriodDBEntity {

    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
