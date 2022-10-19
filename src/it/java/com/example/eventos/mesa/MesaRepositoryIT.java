package com.example.eventos.mesa;

import com.example.eventos.personas.Personas;
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
class MesaRepositoryIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MesaRepository mesaRepository;

    /*
    @BeforeEach
    public void setUp(){
        Mesa mesa0 = new MesaReserva("id0", "eventoId1", new Personas(10, 2), 3, "descripcion", "Pepe", true);
        Mesa mesa1 = new MesaReserva("id1", "eventoId1", new Personas(10, 2), 1, "descripcion", "Pepe", true);
        Mesa mesa2 = new MesaReserva("id2", "eventoId1", "Antonio", new Personas(8, 2), 2, false, "descripcion");
        Mesa mesa3 = new Mesa("id3", "eventoId2", new Personas(6, 1), 1, "descripcion");
        Mesa mesa4 = new Mesa("id123", "eventoId2", new Personas(4, 1), 2, "descripcion");
        mongoTemplate.insert(mesa0);
        mongoTemplate.insert(mesa1);
        mongoTemplate.insert(mesa2);
        mongoTemplate.insert(mesa3);
        mongoTemplate.insert(mesa4);
    }

    @Test
    void findByEventoTest(){
        Mesa mesa0 = new Mesa("id0", "eventoId1", "Pepe", new Personas(10, 2), 3, true, "descripcion");
        Mesa mesa1 = new Mesa("id1", "eventoId1", "Pepe", new Personas(10, 2), 1, true, "descripcion");
        Mesa mesa2 = new Mesa("id2", "eventoId1", "Antonio", new Personas(8, 2), 2, false, "descripcion");
        List<Mesa> expectedMesas = new ArrayList<>();
        expectedMesas.add(mesa0);
        expectedMesas.add(mesa1);
        expectedMesas.add(mesa2);

        assertEquals(expectedMesas, mesaRepository.findByEventoId("eventoId1"));
    }

    @Test
    void findByEventoOrderByNumeroTest(){
        Mesa mesa0 = new Mesa("id0", "eventoId1", "Pepe", new Personas(10, 2), 3, true, "descripcion");
        Mesa mesa1 = new Mesa("id1", "eventoId1", "Pepe", new Personas(10, 2), 1, true, "descripcion");
        Mesa mesa2 = new Mesa("id2", "eventoId1", "Antonio", new Personas(8, 2), 2, false, "descripcion");
        List<Mesa> expectedMesas = new ArrayList<>();
        expectedMesas.add(mesa1);
        expectedMesas.add(mesa2);
        expectedMesas.add(mesa0);

        assertEquals(expectedMesas, mesaRepository.findByEventoIdOrderByNumeroAsc("eventoId1"));
    }

    @Test
    void saveTest(){
        Mesa mesaExpected = new Mesa("eventoId3", "Jose", new Personas(10, 1), 1, true, "descripcion");

        assertEquals(mesaExpected, mesaRepository.save(mesaExpected));
    }

    @Test
    void deleteTest(){
        Mesa mesa0 = new Mesa("id0", "eventoId1", "Pepe", new Personas(10, 2), 3, true, "descripcion");
        Mesa mesa1 = new Mesa("id1", "eventoId1", "Pepe", new Personas(10, 2), 1, true, "descripcion");
        Mesa mesa2 = new Mesa("id2", "eventoId1", "Antonio", new Personas(8, 2), 2, false, "descripcion");
        Mesa mesa3 = new Mesa("id3", "eventoId2", new Personas(6, 1), 1, "descripcion");
        Mesa mesa4 = new Mesa("id123", "eventoId2", new Personas(4, 1), 2, "descripcion");

        mesaRepository.delete(mesa4);

        List<Mesa> expectedMesas = new ArrayList<>();
        expectedMesas.add(mesa0);
        expectedMesas.add(mesa1);
        expectedMesas.add(mesa2);
        expectedMesas.add(mesa3);

        assertEquals(expectedMesas, mesaRepository.findAll());
    }

    @Test
    void deleteByEventoIdTest(){
        Mesa mesa3 = new Mesa("id3", "eventoId2", new Personas(6, 1), 1, "descripcion");
        Mesa mesa4 = new Mesa("id123", "eventoId2", new Personas(4, 1), 2, "descripcion");

        mesaRepository.deleteByEventoId("eventoId1");

        List<Mesa> expectedMesas = new ArrayList<>();
        expectedMesas.add(mesa3);
        expectedMesas.add(mesa4);

        assertEquals(expectedMesas, mesaRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }

     */
}
