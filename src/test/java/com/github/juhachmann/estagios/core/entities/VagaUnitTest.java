package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.core.application.IVagaDB;
import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VagaUnitTest {

    User userA;
    User userB;
    User userC;
    User userD;
    List<User> allUsers = new ArrayList<>();
    List<UserEntityDTO> usersDTOs = new ArrayList<>();

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

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void anyUserCanCreateVaga() {
        usersDTOs.forEach(user -> {
            assertDoesNotThrow(() -> Vaga.create(new IVagaDB(), user));
        });
    }

    @Test
    void createdVagaHasUserAsOwner() {
        usersDTOs.forEach(user -> {
            var created = Vaga.create(new IVagaDB(), user);
            assertEquals(user, created.getOwner());
        });
    }

    @Test
    void canUpdateOrDeleteVagaOwnedByUser() {
        usersDTOs.forEach(user -> {
            var created = Vaga.create(new IVagaDB(), user);
            var vaga = new Vaga(created);

            assertDoesNotThrow(() -> vaga.updateFromUser(new IVagaDB(), user));
            assertDoesNotThrow(() -> vaga.deleteFromUser(user));
        });
    }

    @Test
    void tryToUpdateOrDeleteVagaOwnedByOtherUserThrowsException() {
        usersDTOs.forEach(creator -> {
            var created = Vaga.create(new IVagaDB(), creator);
            var vaga = new Vaga(created);
            usersDTOs.forEach(user -> {
                if (!creator.equals(user)) {
                    assertThrows(UnauthorizedAccessException.class, () -> vaga.updateFromUser(new IVagaDB(), user));
                    assertThrows(UnauthorizedAccessException.class,() -> vaga.deleteFromUser(user));
                }
            });
        });
    }

    @Test
    void canSeeVagaPrivateInfoIfOwner() {
        usersDTOs.forEach(user -> {
            var created = Vaga.create(new IVagaDB(), user);
            var vaga = new Vaga(created);
            assertDoesNotThrow(() -> vaga.showPrivateInfo(user));
        });
    }

    @Test
    void tryAccessVagaPrivateInfoIfNotOwnerThrowsException() {
        usersDTOs.forEach(creator -> {
            var created = Vaga.create(new IVagaDB(), creator);
            var vaga = new Vaga(created);
            usersDTOs.forEach(user -> {
                if (!creator.equals(user)) {
                    assertThrows(UnauthorizedAccessException.class, () -> vaga.showPrivateInfo(user));
                }
            });
        });
    }

    @Test
    void canSeeVagaPublicInfoIfOwnerOrReceiver() {

        var vagaDTO = new IVagaDB();
        vagaDTO.getReceivers().add(userDTOA);

        var created = Vaga.create(vagaDTO, userDTOC);
        var vaga = new Vaga(created);

        assertDoesNotThrow(() -> vaga.showPublicInfo(userDTOA));
        assertDoesNotThrow(() -> vaga.showPublicInfo(userDTOC));

        assertThrows(UnauthorizedAccessException.class, () -> vaga.showPublicInfo(userDTOB));
        assertThrows(UnauthorizedAccessException.class, () -> vaga.showPublicInfo(userDTOD));

    }

    @Test
    void onlyIEsCanReceiveVaga() {

        List<UserEntityDTO> receivers = new ArrayList<>();
        usersDTOs.forEach(user -> receivers.add(user));

        var vagaDTO = new IVagaDB();
        vagaDTO.setReceivers(receivers);

        var created = Vaga.create(vagaDTO, userDTOA);

        created.getReceivers().forEach(
                receiver -> assertTrue(receiver.isIe()));

    }

//    @Test
//    void vagaWithoutReceiversIsNOTExclusive() {
//        assertTrue(vaga.getReceivers().isEmpty());
//        assertFalse(vaga.isExclusive());
//    }
//
//    @Test
//    void vagaWithSpecificReceiverIsExclusive() {
//        List<User> receivers = new ArrayList<>();
//        receivers.add(userA);
//        vaga.setReceivers(receivers);
//        assertFalse(vaga.getReceivers().isEmpty());
//        assertTrue(vaga.isExclusive());
//        vaga.setReceivers(null);
//        assertTrue(vaga.getReceivers().isEmpty());
//        assertFalse(vaga.isExclusive());
//    }
//

}
