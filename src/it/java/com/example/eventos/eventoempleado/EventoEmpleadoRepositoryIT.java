package com.example.eventos.eventoempleado;

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
class EventoEmpleadoRepositoryIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EventoEmpleadoRepository eventoEmpleadoRepository;

    @BeforeEach
    public void setUp(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("id1", "idEvento1", "idEmpleado1", "tipo1", "nombre1", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("id2", "idEvento2", "idEmpleado2", "tipo2", "nombre2", false, false, 1);

        mongoTemplate.insert(eventoEmpleado1);
        mongoTemplate.insert(eventoEmpleado2);
    }

    @Test
    void findByIdEventoTest(){
        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado("id1", "idEvento1", "idEmpleado1", "tipo1", "nombre1", true, true, 0);
        eventoEmpleadosExpected.add(eventoEmpleadoExpected);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByIdEvento("idEvento1"));
    }

    @Test
    void findByIdEmpleadoTest(){
        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado("id2", "idEvento2", "idEmpleado2", "tipo2", "nombre2", false, false, 1);

        eventoEmpleadosExpected.add(eventoEmpleadoExpected);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByIdEmpleado("idEmpleado2"));
    }

    @Test
    void findEventoEmpleadoByIdTest(){
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado("id1", "idEvento1", "idEmpleado1", "tipo1", "nombre1", true, true, 0);

        assertEquals(eventoEmpleadoExpected, eventoEmpleadoRepository.findEventoEmpleadoById("id1"));
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
