package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.core.entities.Vaga;

import java.util.List;

public interface VagaRepository {
    Vaga save(Vaga created);
    Vaga findById(Long vagaId);
    void delete(Vaga vaga);
    List<Vaga> findByExclusive(boolean exclusive);
}
