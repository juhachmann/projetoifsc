package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.core.application.VagaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VagaTest {

    static class VagaRepositoryImpl implements VagaRepository {

        @Override
        public Vaga save(Vaga created) {
            return created;
        }

        @Override
        public Vaga findById(Long vagaId) {
            return null;
        }

        @Override
        public void delete(Vaga vaga) {

        }

        @Override
        public List<Vaga> findByExclusive(boolean exclusive) {
            return null;
        }
    }

    @Mock
    VagaRepository repository;

    @InjectMocks
    Vaga vaga;

    User userA = new User(1L, true);
    User userB = new User(2L, true);
    User userC = new User(3L, false);
    User userD = new User(4L, false);
    List<User> allUsers = new ArrayList<>();


    @BeforeEach
    void setUp() {
        vaga = new Vaga(new VagaRepositoryImpl());

        allUsers.add(userA);
        allUsers.add(userB);
        allUsers.add(userC);
        allUsers.add(userD);
    }


    @Test
    void onlyIEsCanReceiveVaga() {
        List<User> receivers = new ArrayList<>();
        allUsers.forEach(user -> receivers.add(user));
        vaga.setReceivers(receivers);

        allUsers.forEach(user -> {
            if (user.isIe()) {
                assertTrue(vaga.getReceivers().contains(user));
            } else {
                assertFalse(vaga.getReceivers().contains(user));
            }
        });
    }

    @Test
    void vagaWithoutReceiversIsNOTExclusive() {
        assertTrue(vaga.getReceivers().isEmpty());
        assertFalse(vaga.isExclusive());
    }

    @Test
    void vagaWithSpecificReceiverIsExclusive() {
        List<User> receivers = new ArrayList<>();
        receivers.add(userA);
        vaga.setReceivers(receivers);
        assertFalse(vaga.getReceivers().isEmpty());
        assertTrue(vaga.isExclusive());
        vaga.setReceivers(null);
        assertTrue(vaga.getReceivers().isEmpty());
        assertFalse(vaga.isExclusive());
    }


}
