package com.example.eventos.eventoEmpleado;

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
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("id1", "eventoId1", "empleadoId1", "tipo1", "nombre1", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("id2", "eventoId2", "empleadoId2", "tipo2", "nombre2", false, false, 1);

        mongoTemplate.insert(eventoEmpleado1);
        mongoTemplate.insert(eventoEmpleado2);
    }

    @Test
    void findByEventoIdTest(){
        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado("id1", "eventoId1", "empleadoId1", "tipo1", "nombre1", true, true, 0);
        eventoEmpleadosExpected.add(eventoEmpleadoExpected);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByEventoId("eventoId1"));
    }

    @Test
    void findByEmpleadoIdTest(){
        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado("id2", "eventoId2", "empleadoId2", "tipo2", "nombre2", false, false, 1);

        eventoEmpleadosExpected.add(eventoEmpleadoExpected);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByEmpleadoId("empleadoId2"));
    }

    @Test
    void findEventoEmpleadoByIdTest(){
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado("id1", "eventoId1", "empleadoId1", "tipo1", "nombre1", true, true, 0);

        assertEquals(eventoEmpleadoExpected, eventoEmpleadoRepository.findEventoEmpleadoById("id1"));
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
