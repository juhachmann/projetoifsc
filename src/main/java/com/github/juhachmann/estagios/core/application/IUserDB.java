package com.github.juhachmann.estagios.core.application;

import java.util.Collection;
import java.util.List;

public interface IUserDB {

    boolean getIe();

    String getId();
    void setId(String id);

    Collection<? extends IVagaDB> getExclusiveReceivedVagas();
    Collection<? extends IVagaDB> getOwnedVagas();

    IUserDB getPrivateProfile();

    IUserDB getPublicProfile();

}
