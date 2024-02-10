package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.core.entities.User;
import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;

public class UserHandler {

    AppHandler appHandler;

    public UserHandler(AppHandler handler) {
        this.appHandler = handler;
    }

    public User seePrivateProfile (User loggedUser ) {
        return loggedUser.showSelfProfile();
    }

    public User seePublicProfile ( User loggedUser, User targetUser ) throws UnauthorizedAccessException {
        return loggedUser.showProfile( targetUser );
    }


}
