package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.core.entities.User;
import com.github.juhachmann.estagios.core.entities.Vaga;
import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;
import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * Entry points for all the application use cases
 * Define the implemented repositories in the class constructor
 */
public class Handler {

//    VagaRepository vagaRepo;
//    UserRepository userRepo;

    ModelMapper mapper = new ModelMapper();

    public Handler() { }

    public Handler (VagaRepository vagaRepositoryImpl, UserRepository userRepositoryImpl) {
//        this.vagaRepo = vagaRepositoryImpl;
//        this.userRepo = userRepositoryImpl;
    }


    public UserPrivateProfileDTO createUser(UserPrivateProfileDTO newUser) {
        var user = mapper.map(newUser, User.class);
        return mapper.map(
                user.createNewUser(),
                UserPrivateProfileDTO.class);
    }

    public UserPrivateProfileDTO updateUser(String username, UserPrivateProfileDTO newData) {
        var user = User.get(username);
        mapper.map(newData, user);
        return mapper.map(
                user.updateUser(),
                UserPrivateProfileDTO.class);
    }

    public void deleteUser (String username) {
        var user = User.get(username);
        user.deleteUser();
    }

    public UserPublicProfileDTO seeUserPublicProfile(String username, Long id) {
        var user = User.get(username);
        var targetUser = User.get(id);
        return mapper.map(
                user.seePublicProfile(targetUser),
                UserPublicProfileDTO.class
        );
    }

    public UserPrivateProfileDTO seeUserPrivateProfile(String username) {
        var user = User.get(username);
        return mapper.map(
                user.seePrivateProfile(),
                UserPrivateProfileDTO.class
        );
    }

//    public UserSettingsDTO seeUserPrivateSettings(String username) {
//        var user = User.get(username);
//        return mapper.map(
//                user.seePrivateSettings(),
//                UserSettingsDTO.class
//        );
//    }


    public VagaPrivateSettingsDTO createVaga(String username, VagaPrivateSettingsDTO newData) {
        var user = User.get(username);
        var vaga = mapper.map(newData, Vaga.class);
        return mapper.map(
                user.createVaga(vaga),
                VagaPrivateSettingsDTO.class);
    }

    public VagaPrivateSettingsDTO updateVaga(String username, Long vagaId, VagaPrivateSettingsDTO newData) throws UnauthorizedAccessException {
        var user = User.get(username);
        var vaga = Vaga.get(vagaId);
        mapper.map(newData, vaga);
        return mapper.map(
                user.updateVaga(vaga),
                VagaPrivateSettingsDTO.class);
    }

    public void deleteVaga(String username, Long vagaId) throws UnauthorizedAccessException {
        var user = User.get(username);
        var vaga = Vaga.get(vagaId);
        user.deleteVaga(vaga);
    }

    public VagaPublicDetailsDTO seeVagaPublic(String username, Long vagaId) throws UnauthorizedAccessException {
        var user = User.get(username);
        var vaga = Vaga.get(vagaId);
        return mapper.map(
                user.seeVaga(vaga),
                VagaPublicDetailsDTO.class
        );
    }

    public VagaPrivateSettingsDTO seeVagaPrivate(String username, Long vagaId) throws UnauthorizedAccessException {
        var user = User.get(username);
        var vaga = Vaga.get(vagaId);
        return mapper.map (
                user.seeVagaSettings(vaga),
                VagaPrivateSettingsDTO.class
        );
    }

    public List<VagaPrivateSettingsDTO> seeAllOwnedVagas(String username) {
        var user = User.get(username);
        List<Vaga> ownedVagas = user.getOwnedVagas();
        return ownedVagas
                .stream()
                .map(vaga -> mapper.map(vaga, VagaPrivateSettingsDTO.class))
                .toList();
    }

    public List<VagaPublicDetailsDTO> seeAllReceivedVagas(String username) throws UnauthorizedAccessException {
        var user = User.get(username);
        List<Vaga> receivedVagas = user.seeAllReceivedVagas();
        return receivedVagas
                .stream()
                .map(vaga -> mapper.map(vaga, VagaPublicDetailsDTO.class))
                .toList();
    }


}
