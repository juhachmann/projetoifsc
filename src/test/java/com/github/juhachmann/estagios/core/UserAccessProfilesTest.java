package com.github.juhachmann.estagios.core;

import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilDTO;
import com.github.juhachmann.estagios.api.resources.userPerfil.UserPerfilDTO;
import com.github.juhachmann.estagios.core.application.AppHandler;
import com.github.juhachmann.estagios.core.entities.User;
import com.github.juhachmann.estagios.core.entities.Vaga;
import static org.junit.jupiter.api.Assertions.*;

import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserAccessProfilesTest {

    User nobanks;
    User ifsc;
    User ufsc;
    User giggle;
    Vaga vaga;
    AppHandler handler = new AppHandler();
    List<User> receivers;
    List<User> companies;
    List<User> schools;
    List<User> allUsers;

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

        schools = new ArrayList<>();
        companies = new ArrayList<>();
        allUsers = new ArrayList<>();

        schools.add(ifsc);
        schools.add(ufsc);

        companies.add(nobanks);
        companies.add(giggle);

        allUsers.addAll(schools);
        allUsers.addAll(companies);

    }

    @Test
    void userCanSeeItsOwnPrivateProfile( ) {
        allUsers.forEach ( user -> assertDoesNotThrow(() -> handler.seePrivateProfile(user) ) ) ;
    }

    @Test
    void userCanSeeItsOwnPublicProfile( ) {
        allUsers.forEach ( user -> assertDoesNotThrow(() -> handler.seePublicProfile(user, user) ) ) ;
    }

    @Test
    void userCanSeePublicSchoolProfile ( ) {
        allUsers.forEach ( user -> {
            assertDoesNotThrow(() -> handler.seePublicProfile(user, ifsc) );
            assertDoesNotThrow(() -> handler.seePublicProfile(user, ufsc) );
        } ) ;
    }

    @Test
    void schoolCanSeeCompanyProfileOnlyIfReceivesVagaFromIt( ) {
        assertDoesNotThrow(() -> handler.seePublicProfile(ifsc, nobanks) );
        assertThrows(UnauthorizedAccessException.class, () -> handler.seePublicProfile( ifsc, giggle ) );
        assertThrows(UnauthorizedAccessException.class, () -> handler.seePublicProfile( ufsc, nobanks ) );
        assertThrows(UnauthorizedAccessException.class, () -> handler.seePublicProfile( ufsc, giggle ) );
    }

    @Test
    void companyCanNeverSeeOtherCompanyProfile (  ) {
        assertThrows(UnauthorizedAccessException.class, () -> handler.seePublicProfile( nobanks, giggle ) );
        assertThrows(UnauthorizedAccessException.class, () -> handler.seePublicProfile( giggle, nobanks ) );
    }


}
