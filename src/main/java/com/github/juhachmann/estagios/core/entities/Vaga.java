package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.core.application.VagaRepository;

import java.util.ArrayList;
import java.util.List;

public class Vaga {

    // TODO : Descobrir : Como injetar o negócio aqui
    static VagaRepository repository;

    private Long id;
    private User owner;
    private boolean exclusive = false;
    private List<String> messages = new ArrayList<>();
    private List<User> receivers = new ArrayList<>();
    private VagaDetails details = new VagaDetails();
    private VagaSettings settings = new VagaSettings();

    public Vaga() { }

    public Vaga(VagaRepository repository) {
        Vaga.repository = repository;
    }

    public Vaga(Long id, User owner, List<String> messages, List<User> receivers, VagaDetails details, VagaSettings settings) {
        this.id = id;
        this.owner = owner;
        this.messages = messages;
        this.setReceivers(receivers);
        this.details = details;
        this.settings = settings;
    }


    public static Vaga get(Long vagaId) {
        return repository.findById(vagaId);
    }

    public static List<Vaga> getAllNotExclusive() {
        return repository.findByExclusive(false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<User> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<User> receivers) {
        if (receivers == null) {
            this.getReceivers().clear();
        }
        else {
            receivers.forEach(this::validateReceiver);
        }
        this.exclusive = !this.getReceivers().isEmpty();
    }

    public VagaDetails getDetails() {
        return details;
    }

    public void setDetails(VagaDetails details) {
        this.details = details;
    }

    public VagaSettings getSettings() {
        return settings;
    }

    public void setSettings(VagaSettings settings) {
        this.settings = settings;
    }

    public Vaga create() {
        return repository.save(this);
    }

    public Vaga update() {
        return repository.save(this);
    }

    public void delete() {
        repository.delete(this);
    }

    public void addReceiver(User user) {
        this.validateReceiver(user);
    }

    private void validateReceiver(User user) {
        if(user.isValidReceiver()) {
            this.receivers.add(user);
        } else {
            this.messages.add(user.getProfile().getName() + " is not a valid receiver");
        }
    }

}
