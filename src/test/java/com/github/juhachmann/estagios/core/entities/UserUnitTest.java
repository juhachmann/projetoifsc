package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.core.application.IVagaDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UserUnitTest {

    User userA;
    User userB;
    User userC;
    User userD;
    List<User> allUsers = new ArrayList<>();
    List<UserEntityDTO> usersDTOs = new ArrayList<>();
    Vaga vaga;
    UserEntityDTO userDTOA;
    UserEntityDTO userDTOB;
    UserEntityDTO userDTOC;
    UserEntityDTO userDTOD;


    @BeforeEach
    void setUp() {
        userDTOA = new UserEntityDTO(1L, true);
        userDTOB = new UserEntityDTO(2L, true);
        userDTOC = new UserEntityDTO(3L, false);
        userDTOD = new UserEntityDTO(4L, false);
        usersDTOs.add(userDTOA);
        usersDTOs.add(userDTOB);
        usersDTOs.add(userDTOC);
        usersDTOs.add(userDTOD);

        userA = new User(userDTOA);
        userB = new User(userDTOB);
        userC = new User(userDTOC);
        userD = new User(userDTOD);
        allUsers.add(userA);
        allUsers.add(userB);
        allUsers.add(userC);
        allUsers.add(userD);

        vaga = new Vaga(new IVagaDB());
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void createsUser() {
        assertDoesNotThrow(()-> User.create(new UserEntityDTO(2L, false)));
    }

    @Test
    void updatesUser() {
        var created = User.create(new UserEntityDTO(1L, true));
        var user = new User(created);
        assertDoesNotThrow(()-> user.updateUser(new UserEntityDTO(2L, false)));
    }

    @Test
    void deletesUser() {
        var created = User.create(new UserEntityDTO(1L, true));
        var user = new User(created);
        assertDoesNotThrow(user::deleteUser);
    }

    @Test
    void canSeeItsOwnPrivateProfile() {
        allUsers.forEach(user -> {
            assertDoesNotThrow(user::seePrivateProfile);
        });
    }

    @Test
    void canSeeAnyUserPublicProfile() {
        allUsers.forEach(mainUser ->
                usersDTOs.forEach(seenUser -> {
                    assertDoesNotThrow(() -> mainUser.seePublicProfile(seenUser));
        }));
    }


}
