package com.example.eventos;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class EventoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EventoRepository eventoRepository;

    @BeforeEach
    public void setUp(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", new Date());
        Evento evento2 = new Evento("Comunión", "Cena", 100, 10, "Aielo de Malferit", new Date());
        mongoTemplate.insert(evento1);
        mongoTemplate.insert(evento2);
    }

    @Test
    public void findEventoByTipoTest(){
        Evento eventoExpected = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", new Date());
        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(eventoExpected);

        assertEquals(eventosExpected, eventoRepository.findEventoByTipo("Boda"));
    }

    @Test
    public void findAllTest(){
        Evento eventoExpected1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", new Date());
        Evento eventoExpected2 = new Evento("Comunión", "Cena", 100, 10, "Aielo de Malferit", new Date());
        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(eventoExpected1);
        eventosExpected.add(eventoExpected2);

        assertEquals(eventosExpected, eventoRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
