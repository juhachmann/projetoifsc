package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.core.application.UserRepository;
import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;

import java.util.ArrayList;
import java.util.List;

public class User {

    // TODO : Descobrir : Como a definição do repositório vai vir parar aqui
    static UserRepository repository;

    private Long id;
    private boolean ie;
    private UserProfile profile = new UserProfile();
    private UserCredentials credentials = new UserCredentials();
    private UserSettings settings = new UserSettings();
    private List<Vaga> ownedVagas;
    private List<Vaga> exclusiveReceivedVagas;

    public User() {
    }

    public User(Long id, boolean ie) {
        this.id = id;
        this.ie = ie;
    }

    public User(Long id, boolean ie, UserProfile profile, UserCredentials credentials, UserSettings settings, List<Vaga> ownedVagas, List<Vaga> receivedVagas) {
        this.id = id;
        this.ie = ie;
        this.profile = profile;
        this.credentials = credentials;
        this.settings = settings;
        this.ownedVagas = ownedVagas;
        this.exclusiveReceivedVagas = receivedVagas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

    public List<Vaga> getOwnedVagas() {
        return ownedVagas;
    }

    public void setOwnedVagas(List<Vaga> ownedVagas) {
        this.ownedVagas = ownedVagas;
    }

    public List<Vaga> getExclusiveReceivedVagas() {
        return exclusiveReceivedVagas;
    }

    public void setExclusiveReceivedVagas(List<Vaga> exclusiveReceivedVagas) {
        this.exclusiveReceivedVagas = exclusiveReceivedVagas;
    }


/*
 * ================================
 *  Business Logic
 * ================================
 */

    public static User get(String username) {
        return repository.findByUsername(username);
    }

    public static User get(Long id) {
        return repository.findById(id);
    }

    private boolean isOwner(Vaga vaga) {
        return vaga.getOwner().equals(this);
    }

    private boolean isReceiver(Vaga vaga) {
        return vaga.getReceivers().contains(this);
    }

    public boolean isValidReceiver() {
        return this.ie;
    }


    // TODO : DTO : Definir estas estruturas de User : Settings, Profile, PublicProfile, PrivateProfile

    public User createNewUser() {
        return repository.save(this);
    }

    public User updateUser() {
        return repository.save(this);
    }

    public void deleteUser() {
        repository.delete(this);
    }

    public UserSettings seePrivateSettings() {
        return this.settings;
    }

    public User seePublicProfile(User user) {
        return user.showPublicProfile();
    }

    public User showPublicProfile() {
        return this;
    }

    public User seePrivateProfile() {
        return this;
    }

    public Vaga createVaga(Vaga vaga) {
        vaga.setOwner(this);
        return vaga.create();
    }

    public Vaga updateVaga(Vaga vaga) throws UnauthorizedAccessException {
        if (this.isOwner(vaga)) {
            return vaga.update();
        }
        throw new UnauthorizedAccessException("User must be owner to update record");
    }

    public void deleteVaga(Vaga vaga) throws UnauthorizedAccessException {
        if ( this.isOwner(vaga) ) {
            vaga.delete();
        } else {
            throw new UnauthorizedAccessException("User must be owner to delete record");
        }
    }

    public List<Vaga> seeAllReceivedVagas() throws UnauthorizedAccessException {
        List<Vaga> receivedVagas = new ArrayList<>();
        receivedVagas.addAll(Vaga.getAllNotExclusive());
        receivedVagas.addAll(this.getExclusiveReceivedVagas());
        return receivedVagas;
    }

    public VagaSettings seeVagaSettings(Vaga vaga) throws UnauthorizedAccessException {
        if ( this.isOwner(vaga) ) {
            return vaga.getSettings();
        }
        throw new UnauthorizedAccessException("User must be owner to see the job private settings");
    }

    public VagaDetails seeVaga(Vaga vaga) throws UnauthorizedAccessException {
        if ( this.isOwner(vaga) || this.isReceiver(vaga) || ( this.isIe() && !vaga.isExclusive() ) ) {
            return vaga.getDetails();
        }
        throw new UnauthorizedAccessException("User must be owner or receiver to see the job");
    }

}
