package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.core.entities.Vaga;

import java.util.List;
import java.util.Optional;

public interface VagaRepository {

    List<Vaga> findByOwner ( Long ownerId ) ;

    List<Vaga> findByReceiver ( Long receiverId ) ;

    List<Vaga> findByOwnerAndReceiver ( Long ownerId , Long receiverId ) ;

    Vaga save (Vaga vaga) ;

    Vaga update (Long vagaId, Vaga vaga );

    void delete ( Long vagaId ) ;

    void deleteAll (  ) ;

    void delete ( List<Long> vagasIds ) ;

    Optional<Vaga> findById (Long id );

}
