package com.github.juhachmann.estagios.core.entities;

import java.util.List;

public interface Creator {

    Vaga create ( Vaga vaga , List<Receiver> receivers ) ;
    Vaga update ( Vaga vaga ) ;
    Vaga delete ( Vaga vaga ) ;
    void showVagaPrivateView ( Vaga vaga ) ;
    void showVagaPublicView ( Vaga vaga ) ;
    Boolean offersVagaFor( User user );
    Boolean isOwner(Vaga vaga) ;

}
