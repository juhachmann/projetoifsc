package com.github.juhachmann.estagios.learnJPA;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public class TesteJPAClasses {

    @Entity
    @Table(name = "medico")
    static class Medico {

        @Id
        private Long id;

        private String name;

        @ManyToMany
        @JoinTable(name = "consultorios_medicos",
                joinColumns = @JoinColumn(name = "medico_id"),
                inverseJoinColumns = @JoinColumn(name = "consultorio_id")
        )
        private List<Consultorio> consultorios;

        public Medico() {
        }

        public Medico(Long id, String name, List<Consultorio> consultorios) {
            this.id = id;
            this.name = name;
            this.consultorios = consultorios;
        }
    }

    @Entity
    @Table(name = "consultorio")
    static class Consultorio {
        @Id
        private Long id;

        private String name;

        @ManyToMany(mappedBy = "consultorios")
        private List<Medico> medicos;

        public Consultorio() {
        }

        public Consultorio(Long id, String name) {
            this.id = id;
            this.name = name;
        }

    }


}
