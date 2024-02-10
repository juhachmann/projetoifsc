package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.api.resources.authUserConfig.AuthUserConfigDTO;
import com.github.juhachmann.estagios.api.resources.authUserVaga.AuthUserVagaDTO;
import com.github.juhachmann.estagios.api.resources.userPerfil.UserPerfilDTO;
import com.github.juhachmann.estagios.api.resources.vagas.VagaDTO;
import com.github.juhachmann.estagios.core.application.dto.AppVagaDTO;
import com.github.juhachmann.estagios.core.entities.Receiver;
import com.github.juhachmann.estagios.core.entities.User;
import com.github.juhachmann.estagios.core.entities.Vaga;
import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;

import java.util.ArrayList;
import java.util.List;

// Essa é a porta ?
// Onde estão os pontos de entrada
// Não dá de pedir pra mandar entidades já aqui, né /
// Pq o que a API vai ver são os DTOs & estes contratos que estão aí embaixo
// vai chamar algo como app.getVagaPublicMode( autenticacao do user , id da vaga )
// O resto é com essa parte do código
// Essa questão de Vaga pública ou privada, essa questão do que visualiza o que, isso é lógica de negócio?
// Na minha opinião, sim...
// Justamente pra não virar uma zona
// Mas o get Vagas For {id} ou get Vagas From {id}, isso resolvia com outras queries não precisava de uma entrada espe´ciifica pra isso

// Os DTOs de entrada e saída tbm são Contratos

public class AppHandler {

    UserRepository userRepository;
    VagaRepository vagaRepository;
    ConfigRepository configRepository;

    Mapper mapper;

    VagaHandler vagaHandler;
    UserHandler userHandler;

    public AppHandler() {
        this.vagaHandler = new VagaHandler(this);
        this.userHandler = new UserHandler(this);
    }

    public AppHandler(UserRepository userRepository, VagaRepository vagaRepository, ConfigRepository configRepository, Mapper mapper) {
        // Precisaria passar para os handlers específicos...
        this.userRepository = userRepository;
        this.vagaRepository = vagaRepository;
        this.configRepository = configRepository;
        this.mapper = mapper;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public VagaRepository getVagaRepository() {
        return vagaRepository;
    }

    public ConfigRepository getConfigRepository() {
        return configRepository;
    }



    public Vaga createVaga (Long authUserId , AuthUserVagaDTO vagaDTO  ) {

        return vagaHandler.createVaga( authUserId, vagaDTO );

    }

    public Vaga seeVagaPublicMode(User loggedUser, Vaga vaga ) throws UnauthorizedAccessException {
        return vagaHandler.seeVagaPublicMode( loggedUser, vaga );
    }

    public Vaga seeVagaPrivateMode ( User loggedUser, Vaga vaga ) throws UnauthorizedAccessException {
        return vagaHandler.seeVagaPrivateMode ( loggedUser, vaga );
    }

    public Iterable<Vaga> getVagasOwnedByMe ( User loggedUser ) {
        return vagaHandler.getVagasOwnedByMe(loggedUser);
    }

    public Iterable<Vaga> getVagasForMe ( User loggedUser ) {
        return vagaHandler.getVagasForMe(loggedUser);
    }

    public Iterable<Vaga> getVagasForUserOwnedByMe ( User loggedUser , User receiver ) {
        return vagaHandler.getVagasForUserOwnedByMe(loggedUser, receiver);
    }


    public User seePrivateProfile ( User loggedUser ) {
        return userHandler.seePrivateProfile( loggedUser );
    }

    public User seePublicProfile ( User loggedUser, User targetUser ) throws UnauthorizedAccessException {
        return userHandler.seePublicProfile( loggedUser , targetUser );
    }





    public User mapUser(UserPerfilDTO user ) {

        Long id = user.getKey();
        String name = user.getName();
        Boolean ies = user.isIe();
        return User.createUser(id, name, ies);
    }




}
