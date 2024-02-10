package com.github.juhachmann.estagios.core;

import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilDTO;
import com.github.juhachmann.estagios.api.resources.userPerfil.UserPerfilDTO;
import com.github.juhachmann.estagios.core.application.AppHandler;
import com.github.juhachmann.estagios.core.entities.User;
import com.github.juhachmann.estagios.core.entities.Vaga;
import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UserCreatesVagaTest {

    User nobanks;
    User ifsc;
    User ufsc;
    User giggle;
    Vaga vaga;
    AppHandler handler = new AppHandler();
    List<User> receivers;


    @BeforeEach
    void setUp() {
        AuthUserPerfilDTO loggedUserDTO = new AuthUserPerfilDTO();
        loggedUserDTO.setKey(1L);
        loggedUserDTO.setName("Nobanks");
        loggedUserDTO.setIe(false);

        UserPerfilDTO ifscDTO = new UserPerfilDTO();
        ifscDTO.setKey(2L);
        ifscDTO.setName("IFSC");
        ifscDTO.setIe(true);

        UserPerfilDTO ufscDTO = new UserPerfilDTO();
        ufscDTO.setKey(3L);
        ufscDTO.setName("UFSC");
        ufscDTO.setIe(true);

        UserPerfilDTO giggleDTO = new UserPerfilDTO();
        giggleDTO.setKey(4L);
        giggleDTO.setName("Giggle");
        giggleDTO.setIe(false);

        ufsc = handler.mapUser(ufscDTO);
        giggle = handler.mapUser(giggleDTO);
        nobanks = handler.mapUser( loggedUserDTO );
        ifsc = handler.mapUser( ifscDTO );

        receivers = new ArrayList<>();
        receivers.add(ifsc);
        vaga = handler.createVaga( nobanks , new Vaga() , receivers );

    }

    @Test
    void anyUserCanCreateVaga() {
        assertDoesNotThrow(() -> handler.createVaga( nobanks , new Vaga() , receivers ));
        assertInstanceOf(Vaga.class, handler.createVaga( nobanks , new Vaga() , receivers ));
        assertDoesNotThrow(() -> handler.createVaga( ifsc , new Vaga() , receivers ));
        assertInstanceOf(Vaga.class, handler.createVaga( ifsc , new Vaga() , receivers ));
        assertDoesNotThrow(() -> handler.createVaga( ufsc , new Vaga() , receivers ));
        assertInstanceOf(Vaga.class, handler.createVaga( ufsc , new Vaga() , receivers ));
        assertDoesNotThrow(() -> handler.createVaga( giggle , new Vaga() , receivers ));
        assertInstanceOf(Vaga.class, handler.createVaga( giggle , new Vaga() , receivers ));
    }

    @Test
    void creatorCanSeeVaga() {
        assertDoesNotThrow(() -> handler.seeVagaPublicMode(nobanks, vaga));
        assertDoesNotThrow(() -> handler.seeVagaPrivateMode(nobanks, vaga));
    }

    @Test
    void receiverCanSeeVagaInPublicMode() {
        assertDoesNotThrow(() -> handler.seeVagaPublicMode(ifsc, vaga));
        assertThrows(UnauthorizedAccessException.class, () -> handler.seeVagaPrivateMode(ifsc, vaga));
    }

    @Test
    void otherUsersCannotSeeVaga() {
        assertThrows(UnauthorizedAccessException.class, () -> handler.seeVagaPublicMode(ufsc, vaga));
        assertThrows(UnauthorizedAccessException.class, () -> handler.seeVagaPrivateMode(ufsc, vaga));
        assertThrows(UnauthorizedAccessException.class, () -> handler.seeVagaPublicMode(giggle, vaga));
        assertThrows(UnauthorizedAccessException.class, () -> handler.seeVagaPrivateMode(giggle, vaga));
    }


    @Test
    void companyCannotReceiveVaga() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        receivers.add(giggle);
        Vaga vaga = handler.createVaga(nobanks, new Vaga() , receivers);

        Method method = vaga.getClass().getDeclaredMethod("getRecipients") ;
        method.setAccessible(true) ;
        List<User> receivers = (ArrayList) method.invoke(vaga);
        assertFalse( receivers.contains(giggle) );
        assertThrows(UnauthorizedAccessException.class, () -> handler.seeVagaPublicMode(giggle, vaga));
        assertThrows(UnauthorizedAccessException.class, () -> handler.seeVagaPrivateMode(giggle, vaga));
    }


}
