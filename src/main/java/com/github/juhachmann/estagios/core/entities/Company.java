package com.github.juhachmann.estagios.core.entities;

import java.util.ArrayList;

public class Company<T> extends User {

    public Company(T id, String name, Boolean ies) {
        super();
        this.name = name;
        this.id = id;
        this.isIE = ies;
        this.offersVagasTo = new ArrayList<>();
    }

    @Override
    protected Boolean canAccessPublicProfileOf(User user) {
        return ( this.isSelf(user) || user.isIE() );
    }

    @Override
    protected Boolean canAccessPublicViewOf(Vaga vaga) {
        return this.isOwner(vaga);
    }
}
