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

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private InvitadoRepository invitadoRepository;

    @BeforeEach
    public void setUp(){
        Invitado invitado1 = InvitadoFactory.crearInvitado("id1", "eventoId1", "mesaId1", "Pepe", "Mayor", "Celiaco");
        Invitado invitado2 = InvitadoFactory.crearInvitado("id2", "eventoId1", "mesaId2", "Antonio","Mayor", "");
        Invitado invitado3 = InvitadoFactory.crearInvitado("id3", "eventoId2", "mesaId3", "Jose","Mayor", "Vegano");
        Invitado invitado4 = InvitadoFactory.crearInvitado("id123", "eventoId2", "mesaId4", "Antonia","Mayor", "");
        Invitado invitado5 = InvitadoFactory.crearInvitado("id4", "eventoId2", "mesaId4", "Antonia","Mayor", "");

        mongoTemplate.insert(invitado1);
        mongoTemplate.insert(invitado2);
        mongoTemplate.insert(invitado3);
        mongoTemplate.insert(invitado4);
        mongoTemplate.insert(invitado5);
    }

    @Test
    void findByMesaIdTest(){
        Invitado invitado4 = InvitadoFactory.crearInvitado("id123", "eventoId2", "mesaId4", "Antonia", "Mayor","");
        Invitado invitado5 = InvitadoFactory.crearInvitado("id4", "eventoId2", "mesaId4", "Antonia", "Mayor","");
        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado4);
        expectedInvitados.add(invitado5);

        assertEquals(expectedInvitados, invitadoRepository.findByMesaId("mesaId4"));
    }

    @Test
    void saveTest(){
        Invitado invitadoExpected = InvitadoFactory.crearInvitado("id123", "eventoId2", "mesaId4", "Antonia", "Mayor","");

        assertEquals(invitadoExpected, invitadoRepository.save(invitadoExpected));
    }

    @Test
    void deleteTest(){
        Invitado invitado1 = InvitadoFactory.crearInvitado("id1", "eventoId1", "mesaId1", "Pepe", "Mayor","Celiaco");
        Invitado invitado2 = InvitadoFactory.crearInvitado("id2", "eventoId1", "mesaId2", "Antonio", "Mayor","");
        Invitado invitado3 = InvitadoFactory.crearInvitado("id3", "eventoId2", "mesaId3", "Jose", "Mayor","Vegano");
        Invitado invitado4 = InvitadoFactory.crearInvitado("id123", "eventoId2", "mesaId4", "Antonia", "Mayor","");
        Invitado invitado5 = InvitadoFactory.crearInvitado("id4", "eventoId2", "mesaId4", "Antonia", "Mayor","");

        invitadoRepository.delete(invitado4);

        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado1);
        expectedInvitados.add(invitado2);
        expectedInvitados.add(invitado3);
        expectedInvitados.add(invitado5);

        assertEquals(expectedInvitados, invitadoRepository.findAll());
    }

    @Test
    void deleteByMesaIdTest(){
        Invitado invitado1 = InvitadoFactory.crearInvitado("id1", "eventoId1", "mesaId1", "Pepe", "Mayor","Celiaco");
        Invitado invitado2 = InvitadoFactory.crearInvitado("id2", "eventoId1", "mesaId2", "Antonio", "Mayor","");
        Invitado invitado3 = InvitadoFactory.crearInvitado("id3", "eventoId2", "mesaId3", "Jose", "Mayor","Vegano");

        invitadoRepository.deleteByMesaId("mesaId4");

        List<Invitado> expectedInvitados = new ArrayList<>();
        expectedInvitados.add(invitado1);
        expectedInvitados.add(invitado2);
        expectedInvitados.add(invitado3);

        assertEquals(expectedInvitados, invitadoRepository.findAll());
    }

    @Test
    void deleteByEventoIdTest(){
        Invitado invitado3 = InvitadoFactory.crearInvitado("id3", "eventoId2", "mesaId3", "Jose", "Mayor","Vegano");
        Invitado invitado4 = InvitadoFactory.crearInvitado("id123", "eventoId2", "mesaId4", "Antonia", "Mayor","");
        Invitado invitado5 = InvitadoFactory.crearInvitado("id4", "eventoId2", "mesaId4", "Antonia", "Mayor","");

        invitadoRepository.deleteByEventoId("eventoId1");

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
