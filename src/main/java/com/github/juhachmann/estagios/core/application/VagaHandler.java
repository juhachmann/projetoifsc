package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.api.resources.authUserVaga.AuthUserVagaDTO;
import com.github.juhachmann.estagios.core.entities.Receiver;
import com.github.juhachmann.estagios.core.entities.User;
import com.github.juhachmann.estagios.core.entities.Vaga;
import com.github.juhachmann.estagios.core.exceptions.ResourceNotFoundException;
import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

public class VagaHandler {

    AppHandler appHandler;
    VagaRepository repository;

    public VagaHandler(AppHandler handler) {
        this.appHandler = handler;
        this.repository = appHandler.getVagaRepository();
    }

    public Vaga createVaga(Long authUserId , AuthUserVagaDTO vagaDTO ) throws ResourceNotFoundException {

        var result = appHandler.getUserRepository().findById(authUserId);

        if (!result.isPresent() ) {
           // throw new ResourceNotFoundException();
        }
        var user = result.get();

        List<Long> usersIDToReceiveVaga = vagaDTO.getIes();

        List<User> usersToReceiveVaga = appHandler.getUserRepository().findAllById(usersIDToReceiveVaga);

        Vaga vaga = Vaga.create(vagaDTO);

        var created =  user.create(vagaTO, usersToReceiveVaga);
        var saved = repository.save(created);

        return saved;

    }

    public Vaga seeVagaPublicMode(User loggedUser, Long id ) throws UnauthorizedAccessException {
        // TODO como informar o controller de que pode ter dois erros aqui
        // O NotFound e o Unauthorizaed
        var result = repository.findById ( id );
        if ( result.isPresent() ) {
            var vaga = result.get();
            return loggedUser.showVagaPublicView( vaga );
        }
        return null;
    }

    public Vaga seeVagaPrivateMode ( User loggedUser, Vaga vaga ) throws UnauthorizedAccessException {
        return loggedUser.showVagaPrivateView (  vaga );
    }

    public Iterable<Vaga> getVagasOwnedByMe ( User loggedUser ) {
        return repository.findByOwner(loggedUser.getId());
    }

    public Iterable<Vaga> getVagasForMe ( User loggedUser ) {
        return repository.findByReceiver(loggedUser.getId());
    }

    public Iterable<Vaga> getVagasForUserOwnedByMe ( User loggedUser , User receiver ) {
        return repository.findByOwnerAndReceiver(loggedUser.getId(), receiver.getId());
    }


}
