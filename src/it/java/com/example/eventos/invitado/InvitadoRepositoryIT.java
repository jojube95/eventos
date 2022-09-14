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
class InvitadoRepositoryIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private InvitadoRepository invitadoRepository;

    @BeforeEach
    public void setUp(){
        Invitado invitado1 = new Invitado("id1", "idEvento1", "idMesa1", "Mayor", "Pepe", "Celiaco");
        Invitado invitado2 = new Invitado("id2", "idEvento1", "idMesa2", "Mayor","Antonio", "");
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Mayor","Jose", "Vegano");
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Mayor","Antonia", "");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Mayor","Antonia", "");

        mongoTemplate.insert(invitado1);
        mongoTemplate.insert(invitado2);
        mongoTemplate.insert(invitado3);
        mongoTemplate.insert(invitado4);
        mongoTemplate.insert(invitado5);
    }

    @Test
    void findByIdMesaTest(){
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "Mayor","");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Antonia", "Mayor","");
        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado4);
        expectedInvitados.add(invitado5);

        assertEquals(expectedInvitados, invitadoRepository.findByIdMesa("idMesa4"));
    }

    @Test
    void saveTest(){
        Invitado invitadoExpected = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "Mayor","");

        assertEquals(invitadoExpected, invitadoRepository.save(invitadoExpected));
    }

    @Test
    void deleteTest(){
        Invitado invitado1 = new Invitado("id1", "idEvento1", "idMesa1", "Pepe", "Mayor","Celiaco");
        Invitado invitado2 = new Invitado("id2", "idEvento1", "idMesa2", "Antonio", "Mayor","");
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Jose", "Mayor","Vegano");
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "Mayor","");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Antonia", "Mayor","");

        invitadoRepository.delete(invitado4);

        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado1);
        expectedInvitados.add(invitado2);
        expectedInvitados.add(invitado3);
        expectedInvitados.add(invitado5);

        assertEquals(expectedInvitados, invitadoRepository.findAll());
    }

    @Test
    void deleteByIdMesaTest(){
        Invitado invitado1 = new Invitado("id1", "idEvento1", "idMesa1", "Pepe", "Mayor","Celiaco");
        Invitado invitado2 = new Invitado("id2", "idEvento1", "idMesa2", "Antonio", "Mayor","");
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Jose", "Mayor","Vegano");

        invitadoRepository.deleteByIdMesa("idMesa4");

        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado1);
        expectedInvitados.add(invitado2);
        expectedInvitados.add(invitado3);

        assertEquals(expectedInvitados, invitadoRepository.findAll());
    }

    @Test
    void deleteByIdEventoTest(){
        Invitado invitado3 = new Invitado("id3", "idEvento2", "idMesa3", "Jose", "Mayor","Vegano");
        Invitado invitado4 = new Invitado("id123", "idEvento2", "idMesa4", "Antonia", "Mayor","");
        Invitado invitado5 = new Invitado("id4", "idEvento2", "idMesa4", "Antonia", "Mayor","");

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
