package com.github.juhachmann.estagios.core.entities;

// TODO descobrir como usar herança e interface
// Para fazer isso:
// user.create( vaga, newUser )
// sendo que newUser é um User que implementa determinada interface

import java.util.ArrayList;

public class School<T> extends User implements Receiver {

    public School(T id, String name, Boolean ies) {
        super();
        this.name = name;
        this.id = id;
        this.isIE = ies;
        this.offersVagasTo = new ArrayList<>();
    }

    @Override
    protected Boolean canAccessPublicProfileOf (User user) {
        return this.isSelf(user) || user.isIE() || user.offersVagaFor(this) ;
    }

    @Override
    protected Boolean canAccessPublicViewOf (Vaga vaga) {
        return this.isOwner( vaga ) || vaga.isOfferedTo (this) ;
    }


}
