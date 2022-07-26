package com.example.eventos.evento;

import com.example.eventos.protagonista.Protagonista;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class EventoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EventoRepository eventoRepository;

    Date fecha;

    @BeforeEach
    public void setUp(){
        fecha = new GregorianCalendar(2014, Calendar.JANUARY, 11).getTime();

        Evento evento1 = new Evento("id1", "Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");
        Evento evento2 = new Evento("id2", "Comunión", "Cena", 100, 10, "Aielo de Malferit", fecha, "Comunión-Cena");
        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);
        Evento eventoProtagonistas = new Evento("id123", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        mongoTemplate.insert(evento1);
        mongoTemplate.insert(evento2);
        mongoTemplate.insert(eventoProtagonistas);
    }

    @Test
    public void findEventoByTipoTest(){
        Evento eventoExpected = new Evento("id1", "Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");
        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(eventoExpected);

        assertEquals(eventosExpected, eventoRepository.findEventoByTipo("Boda"));
    }

    @Test
    public void findAllTest(){
        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento eventoExpected1 = new Evento("id1", "Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");
        Evento eventoExpected2 = new Evento("id2", "Comunión", "Cena", 100, 10, "Aielo de Malferit", fecha, "Comunión-Cena");
        Evento eventoExpected3 = new Evento("id123", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(eventoExpected1);
        eventosExpected.add(eventoExpected2);
        eventosExpected.add(eventoExpected3);

        assertEquals(eventosExpected, eventoRepository.findAll());
    }

    @Test
    public void findByIdTest(){
        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento eventoExpected = new Evento("id123", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        assertEquals(eventoExpected, eventoRepository.findEventoById("id123"));
    }

    @Test
    public void saveTest(){
        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento eventoExpected = new Evento("id123", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        assertEquals(eventoExpected, eventoRepository.save(eventoExpected));
    }

    @Test
    public void deleteTest(){
        Evento evento1 = new Evento("id1", "Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");
        Evento evento2 = new Evento("id2", "Comunión", "Cena", 100, 10, "Aielo de Malferit", fecha, "Comunión-Cena");
        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);
        Evento eventoProtagonistas = new Evento("id123", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        eventoRepository.delete(eventoProtagonistas);

        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(evento1);
        eventosExpected.add(evento2);

        assertEquals(eventosExpected, eventoRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
