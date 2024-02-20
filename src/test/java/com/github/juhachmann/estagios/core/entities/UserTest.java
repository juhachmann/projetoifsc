package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    User userA;
    User userB;
    User userC;
    User userD;
    List<User> allUsers = new ArrayList<>();
    Vaga vaga;

    @BeforeEach
    void setUp() {
        userA = new User(1L, true);
        userB = new User(2L, true);
        userC = new User(3L, false);
        userD = new User(4L, false);
        allUsers.add(userA);
        allUsers.add(userB);
        allUsers.add(userC);
        allUsers.add(userD);
        vaga = new Vaga();
    }

    @AfterEach
    void tearDown() {

    }

    // TODO: LEARN : Replace lambda with method reference ( () -> user.something() == user::something )
    @Test
    void canSeeItsOwnPrivateProfile() {
        allUsers.forEach(user -> {
            assertDoesNotThrow(user::seePrivateSettings);
            assertInstanceOf(UserSettings.class, user.seePrivateSettings());
        });
    }

    @Test
    void canSeeAnyUserPublicProfile() {
        allUsers.forEach(mainUser ->
                allUsers.forEach(seenUser -> {
                    assertDoesNotThrow(() -> mainUser.seePublicProfile(seenUser));
                    assertInstanceOf(UserProfile.class, mainUser.seePublicProfile(seenUser));
        }));
    }


    @Test
    void anyUserCanCreateVaga() {
        allUsers.forEach(user -> {
           assertDoesNotThrow(() -> user.createVaga(vaga));
           assertInstanceOf(Vaga.class, user.createVaga(vaga));
        });
    }

    @Test
    void createdVagaHasUserAsOwner() {
        allUsers.forEach(user -> {
            var created = user.createVaga(vaga);
            assertEquals(user.getId(), created.getOwner().getId());
        });
    }

    @Test
    void canUpdateOrDeleteVagaOwnedByUser() {
        allUsers.forEach(user -> {
            var created = user.createVaga(vaga);
            assertDoesNotThrow(() -> user.updateVaga(created));
            try {
                assertInstanceOf(Vaga.class, user.updateVaga(created));
            } catch (UnauthorizedAccessException e) {
                throw new RuntimeException(e);
            }
            try {
                assertEquals(user, user.updateVaga(created).getOwner());
            } catch (UnauthorizedAccessException e) {
                throw new RuntimeException(e);
            }
            assertDoesNotThrow(() -> user.deleteVaga(created));
        });
    }

    @Test
    void tryToUpdateOrDeleteVagaOwnedByOtherUserThrowsException() {
        allUsers.forEach(creator -> {
            var created = creator.createVaga(vaga);
            allUsers.forEach(user -> {
                if (!creator.equals(user)) {
                    assertThrows(UnauthorizedAccessException.class, () -> user.updateVaga(created));
                    assertThrows(UnauthorizedAccessException.class,() -> user.deleteVaga(created));
                }
            });
        });
    }

    @Test
    void canSeeVagaPrivateInfoIfOwner() {
        allUsers.forEach(user -> {
            var created = user.createVaga(vaga);
            assertDoesNotThrow(() -> user.seeVaga(created));
            assertDoesNotThrow(() -> user.seeVagaSettings(created));
        });
    }

    @Test
    void tryAccessVagaPrivateInfoIfNotOwnerThrowsException() {
        allUsers.forEach(creator -> {
            var created = creator.createVaga(vaga);
            allUsers.forEach(user -> {
                if (!creator.equals(user)) {
                    assertThrows(UnauthorizedAccessException.class, () -> user.seeVagaSettings(created));
                }
            });
        });
    }

    @Test
    void canSeeVagaPublicInfoIfOwnerOrReceiver() {

        vaga.addReceiver(userA);
        var created = userC.createVaga(vaga);

        assertDoesNotThrow(() -> userA.seeVaga(created));
        assertDoesNotThrow(() -> userC.seeVaga(created));
        assertThrows(UnauthorizedAccessException.class, () -> userB.seeVaga(created));
        assertThrows(UnauthorizedAccessException.class, () -> userD.seeVaga(created));

    }

    @Test
    void onlyIEsAreValidReceivers() {
        allUsers.forEach(user -> {
            if(user.isIe()) {
                assertTrue(user.isValidReceiver());
            } else {
                assertFalse(user.isValidReceiver());
            }
        });
    }

}
