package com.example.eventos.horarioEvento;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class HorarioEventoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HorarioEventoRepository horarioEventoRepository;

    @BeforeEach
    public void setUp(){
        HorarioEvento horarioEvento1 = new HorarioEvento("Cena");
        HorarioEvento horarioEvento2 = new HorarioEvento("Comida");

        mongoTemplate.insert(horarioEvento1);
        mongoTemplate.insert(horarioEvento2);
    }

    @Test
    void findAllTest(){
        HorarioEvento horarioEventoExpected1 = new HorarioEvento("Cena");
        HorarioEvento horarioEventoExpected2 = new HorarioEvento("Comida");

        List<HorarioEvento> horarioEventosExpected = new ArrayList<>();
        horarioEventosExpected.add(horarioEventoExpected1);
        horarioEventosExpected.add(horarioEventoExpected2);

        assertEquals(horarioEventosExpected, horarioEventoRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
