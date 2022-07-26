package com.example.eventos.invitado;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class InvitadoRepositoryIT {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private InvitadoRepository invitadoRepository;

    @BeforeEach
    public void setUp(){
        Invitado invitado1 = new Invitado("id1", "idEvento1", "idMesa1", "Pepe", "Celiaco");
        Invitado invitado2 = new Invitado("id2", "idEvento1", "idMesa2", "Antonio", "");
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Jose", "Vegano");
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Antonia", "");

        mongoTemplate.insert(invitado1);
        mongoTemplate.insert(invitado2);
        mongoTemplate.insert(invitado3);
        mongoTemplate.insert(invitado4);
        mongoTemplate.insert(invitado5);
    }

    @Test
    public void findByIdMesaTest(){
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Antonia", "");
        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado4);
        expectedInvitados.add(invitado5);

        assertEquals(expectedInvitados, invitadoRepository.findByIdMesa("idMesa4"));
    }

    @Test
    public void saveTest(){
        Invitado invitadoExpected = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "");

        assertEquals(invitadoExpected, invitadoRepository.save(invitadoExpected));
    }

    @Test
    public void deleteTest(){
        Invitado invitado1 = new Invitado("id1", "idEvento1", "idMesa1", "Pepe", "Celiaco");
        Invitado invitado2 = new Invitado("id2", "idEvento1", "idMesa2", "Antonio", "");
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Jose", "Vegano");
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Antonia", "");

        invitadoRepository.delete(invitado4);

        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado1);
        expectedInvitados.add(invitado2);
        expectedInvitados.add(invitado3);
        expectedInvitados.add(invitado5);

        assertEquals(expectedInvitados, invitadoRepository.findAll());
    }

    @Test
    public void deleteByIdMesaTest(){
        Invitado invitado1 = new Invitado("id1", "idEvento1", "idMesa1", "Pepe", "Celiaco");
        Invitado invitado2 = new Invitado("id2", "idEvento1", "idMesa2", "Antonio", "");
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Jose", "Vegano");

        invitadoRepository.deleteByIdMesa("idMesa4");

        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado1);
        expectedInvitados.add(invitado2);
        expectedInvitados.add(invitado3);

        assertEquals(expectedInvitados, invitadoRepository.findAll());
    }

    @Test
    public void deleteByIdEventoTest(){
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Jose", "Vegano");
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Antonia", "");

        invitadoRepository.deleteByIdEvento("idEvento1");

        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado3);
        expectedInvitados.add(invitado4);
        expectedInvitados.add(invitado5);

        assertEquals(expectedInvitados, invitadoRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
