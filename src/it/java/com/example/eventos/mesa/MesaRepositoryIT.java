package com.example.eventos.mesa;

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

    @BeforeEach
    public void setUp(){
        Mesa mesa1 = new Mesa("id1", "idEvento1", "Pepe", 10, 1, true);
        Mesa mesa2 = new Mesa("id2", "idEvento1", "Antonio", 8, 2, false);
        Mesa mesa3 = new Mesa("id3", "idEvento2", 6, 1);
        Mesa mesa4 = new Mesa("id123", "idEvento2", 4, 2);
        mongoTemplate.insert(mesa1);
        mongoTemplate.insert(mesa2);
        mongoTemplate.insert(mesa3);
        mongoTemplate.insert(mesa4);
    }

    @Test
    void findByEventoTest(){
        Mesa mesa1 = new Mesa("id1", "idEvento1", "Pepe", 10, 1, true);
        Mesa mesa2 = new Mesa("id2", "idEvento1", "Antonio", 8, 2, false);
        List<Mesa> expectedMesas = new ArrayList<>();
        expectedMesas.add(mesa1);
        expectedMesas.add(mesa2);

        assertEquals(expectedMesas, mesaRepository.findByIdEvento("idEvento1"));
    }

    @Test
    void saveTest(){
        Mesa mesaExpected = new Mesa("idEvento3", "Jose", 10, 1, true);

        assertEquals(mesaExpected, mesaRepository.save(mesaExpected));
    }

    @Test
    void deleteTest(){
        Mesa mesa1 = new Mesa("id1", "idEvento1", "Pepe", 10, 1, true);
        Mesa mesa2 = new Mesa("id2", "idEvento1", "Antonio", 8, 2, false);
        Mesa mesa3 = new Mesa("id3", "idEvento2", 6, 1);
        Mesa mesa4 = new Mesa("id123", "idEvento2", 4, 2);

        mesaRepository.delete(mesa4);

        List<Mesa> expectedMesas = new ArrayList<>();
        expectedMesas.add(mesa1);
        expectedMesas.add(mesa2);
        expectedMesas.add(mesa3);

        assertEquals(expectedMesas, mesaRepository.findAll());
    }

    @Test
    void deleteByIdEventoTest(){
        Mesa mesa3 = new Mesa("id3", "idEvento2", 6, 1);
        Mesa mesa4 = new Mesa("id123", "idEvento2", 4, 2);

        mesaRepository.deleteByIdEvento("idEvento1");

        List<Mesa> expectedMesas = new ArrayList<>();
        expectedMesas.add(mesa3);
        expectedMesas.add(mesa4);

        assertEquals(expectedMesas, mesaRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
