package com.example.eventos.eventoEmpleado;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.empleado.Empleado;
import com.example.eventos.evento.Evento;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.persona.Persona;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import com.example.eventos.tipoEvento.TipoEvento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class EventoEmpleadoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EventoEmpleadoRepository eventoEmpleadoRepository;

    Date fecha;

    @BeforeEach
    public void setUp(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();

        Evento evento1 = new Evento("idEvento1", new TipoEvento("boda"), new HorarioEvento("comida"), new Personas(40, 13), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true);
        Empleado empleado2 = new Empleado("idEmpleado2", new TipoEmpleado("cocinero"), new Persona("nombre2", "666777999", "correo2"), false);
        Evento evento2 = new Evento("idEvento2", new TipoEvento("comunion"), new HorarioEvento("cena"), new Personas(30, 2), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado3 = new Empleado("idEmpleado3", new TipoEmpleado("camarero"), new Persona("nombre3", "666777828", "correo3"), true);
        Empleado empleado4 = new Empleado("idEmpleado4", new TipoEmpleado("cocinero"), new Persona("nombre4", "666777919", "correo4"), false);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEventoEmpleado1", evento1, empleado1, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEventoEmpleado2", evento1, empleado2, false, 1);
        EventoEmpleado eventoEmpleado3 = new EventoEmpleado("idEventoEmpleado3", evento2, empleado3, true, 0);
        EventoEmpleado eventoEmpleado4 = new EventoEmpleado("idEventoEmpleado4", evento2, empleado4, false, 1);

        mongoTemplate.insert(evento1);
        mongoTemplate.insert(evento2);

        mongoTemplate.insert(empleado1);
        mongoTemplate.insert(empleado2);
        mongoTemplate.insert(empleado3);
        mongoTemplate.insert(empleado4);

        mongoTemplate.insert(eventoEmpleado1);
        mongoTemplate.insert(eventoEmpleado2);
        mongoTemplate.insert(eventoEmpleado3);
        mongoTemplate.insert(eventoEmpleado4);
    }

    @Test
    void findByEventoIdTest(){
        Evento evento1 = new Evento("idEvento1", new TipoEvento("boda"), new HorarioEvento("comida"), new Personas(40, 13), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true);
        Empleado empleado2 = new Empleado("idEmpleado2", new TipoEmpleado("cocinero"), new Persona("nombre2", "666777999", "correo2"), false);

        EventoEmpleado eventoEmpleadoExpected1 = new EventoEmpleado("idEventoEmpleado1", evento1, empleado1, true, 0);
        EventoEmpleado eventoEmpleadoExpected2 = new EventoEmpleado("idEventoEmpleado2", evento1, empleado2, false, 1);
        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        eventoEmpleadosExpected.add(eventoEmpleadoExpected1);
        eventoEmpleadosExpected.add(eventoEmpleadoExpected2);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByEventoId("idEvento1"));
    }

    @Test
    void findByEmpleadoIdTest(){
        Evento evento1 = new Evento("idEvento1", new TipoEvento("boda"), new HorarioEvento("comida"), new Personas(40, 13), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado2 = new Empleado("idEmpleado2", new TipoEmpleado("cocinero"), new Persona("nombre2", "666777999", "correo2"), false);

        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEventoEmpleado2", evento1, empleado2, false, 1);

        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        eventoEmpleadosExpected.add(eventoEmpleado2);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByEmpleadoId("idEmpleado2"));
    }

    @Test
    void findEventoEmpleadoByIdTest(){
        Evento evento2 = new Evento("idEvento2", new TipoEvento("comunion"), new HorarioEvento("cena"), new Personas(30, 2), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado3 = new Empleado("idEmpleado3", new TipoEmpleado("camarero"), new Persona("nombre3", "666777828", "correo3"), true);
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado("idEventoEmpleado3", evento2, empleado3, true, 0);

        assertEquals(eventoEmpleadoExpected, eventoEmpleadoRepository.findEventoEmpleadoById("idEventoEmpleado3"));
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
