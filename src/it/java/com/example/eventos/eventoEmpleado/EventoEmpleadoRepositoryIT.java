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

        Evento evento = new Evento("idEvento", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true);
        Empleado empleado2 = new Empleado("idEmpleado2", new TipoEmpleado("cocinero"), new Persona("nombre2", "666777999", "correo2"), false);
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento, empleado1, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado(evento, empleado2, false, 1);

        mongoTemplate.insert(eventoEmpleado1);
        mongoTemplate.insert(eventoEmpleado2);
    }

    @Test
    void findByEventoIdTest(){
        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        Evento evento = new Evento("idEvento", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true);
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado(evento, empleado1, true, 0);
        eventoEmpleadosExpected.add(eventoEmpleadoExpected);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByEventoId("idEvento"));
    }

    @Test
    void findByEmpleadoIdTest(){
        List<EventoEmpleado> eventoEmpleadosExpected = new ArrayList<>();
        Evento evento = new Evento("idEvento", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado2 = new Empleado("idEmpleado2", new TipoEmpleado("cocinero"), new Persona("nombre2", "666777999", "correo2"), false);

        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado(evento, empleado2, false, 1);

        eventoEmpleadosExpected.add(eventoEmpleadoExpected);

        assertEquals(eventoEmpleadosExpected, eventoEmpleadoRepository.findByEmpleadoId("empleadoId2"));
    }

    @Test
    void findEventoEmpleadoByIdTest(){
        Evento evento = new Evento("idEvento", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true);
        EventoEmpleado eventoEmpleadoExpected = new EventoEmpleado(evento, empleado1, true, 0);

        assertEquals(eventoEmpleadoExpected, eventoEmpleadoRepository.findEventoEmpleadoById("idEmpleado1"));
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
