package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.persona.Persona;
import com.example.eventos.personas.Personas;
import com.example.eventos.protagonista.Protagonista;
import com.example.eventos.tipoEvento.TipoEvento;
import com.example.eventos.tipoProtagonista.TipoProtagonista;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class EventoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EventoRepository eventoRepository;

    Date fecha;

    @BeforeEach
    public void setUp(){
        fecha = new GregorianCalendar(2014, Calendar.JANUARY, 11).getTime();

        Evento evento1 = new Evento("id1", new TipoEvento("boda"), new HorarioEvento("cena"), new Personas(150, 10), "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("id2", new TipoEvento("comunion"), new HorarioEvento("cena"), new Personas(100, 10), "Aielo de Malferit", fecha, "Comunión-Cena", "Sala1");
        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);
        Evento eventoProtagonistas = new Evento("id123", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        mongoTemplate.insert(evento1);
        mongoTemplate.insert(evento2);
        mongoTemplate.insert(eventoProtagonistas);
    }

    @Test
    void findAllTest(){
        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento eventoExpected1 = new Evento("id1", new TipoEvento("boda"), new HorarioEvento("cena"), new Personas(150, 10), "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento eventoExpected2 = new Evento("id2", new TipoEvento("comunion"), new HorarioEvento("cena"), new Personas(100, 10), "Aielo de Malferit", fecha, "Comunión-Cena", "Sala1");
        Evento eventoExpected3 = new Evento("id123", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(eventoExpected1);
        eventosExpected.add(eventoExpected2);
        eventosExpected.add(eventoExpected3);

        assertEquals(eventosExpected, eventoRepository.findAll());
    }

    @Test
    void findByIdTest(){
        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento eventoExpected = new Evento("id123", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        assertEquals(eventoExpected, eventoRepository.findEventoById("id123"));
    }

    @Test
    void findByFechaBeforeTest(){
        mongoTemplate.getDb().drop();

        Date fechaEvento1 = new GregorianCalendar(2022, Calendar.JULY, 2).getTime();
        Date fechaEvento2 = new GregorianCalendar(2022, Calendar.JULY, 4).getTime();

        Date fechaBefore = new GregorianCalendar(2022, Calendar.JULY, 3).getTime();

        Evento evento1 = new Evento("id3", new TipoEvento("boda"), new HorarioEvento("cena"), new Personas(150, 10), "Aielo de Malferit", fechaEvento1, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("id4", new TipoEvento("comunion"), new HorarioEvento("cena"), new Personas(100, 10), "Aielo de Malferit", fechaEvento2, "Comunión-Cena", "Sala1");

        mongoTemplate.insert(evento1);
        mongoTemplate.insert(evento2);

        List<Evento> eventoResponse = eventoRepository.findByFechaBefore(fechaBefore);

        assertEquals(1, eventoResponse.size());
        assertEquals(evento1, eventoResponse.get(0));
    }

    @Test
    void saveTest(){
        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento eventoExpected = new Evento("id123", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        assertEquals(eventoExpected, eventoRepository.save(eventoExpected));
    }

    @Test
    void deleteTest(){
        Evento evento1 = new Evento("id1", new TipoEvento("boda"), new HorarioEvento("cena"), new Personas(150, 10), "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("id2", new TipoEvento("comunion"), new HorarioEvento("cena"), new Personas(100, 10), "Aielo de Malferit", fecha, "Comunión-Cena", "Sala1");
        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);
        Evento eventoProtagonistas = new Evento("id123", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

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
