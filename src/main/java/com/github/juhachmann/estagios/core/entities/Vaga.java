package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.api.resources.authUserVaga.AuthUserVagaDTO;

import java.util.ArrayList;
import java.util.List;

public class Vaga {

    private Long ownerId;
    private final List<Receiver> recipients;

    public Vaga () {
        recipients = new ArrayList<>();
    }

    public Vaga ( Long ownerId ) {
        this();
        this.ownerId = ownerId;
    }

    public Vaga ( Long ownerId, List<Receiver> recipients ) {
        this.ownerId = ownerId;
        this.recipients = recipients;
    }

    public static Vaga create(AuthUserVagaDTO vagaDTO) {
        // Aqui mapeia para uma nova vaga, não é isso?
        return new Vaga();
    }

    protected Long getOwnerId() {
        return ownerId;
    }

    protected void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    protected void addRecipients(Receiver receivers) {
        this.recipients.add(receivers);
    }

    protected Boolean isOfferedTo(Receiver receiver) {
        return recipients.contains(receiver);
    }

}
