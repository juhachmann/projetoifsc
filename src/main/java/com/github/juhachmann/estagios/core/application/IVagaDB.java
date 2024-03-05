package com.github.juhachmann.estagios.core.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IVagaDB {

    String getId();


    void setId(String id);

    void setOwner(IUserDB user);

    Collection<? extends IUserDB> getReceivers();

    IUserDB getOwner();

    IVagaDB getPublicProfile();
    IVagaDB getPrivateProfile();

}
