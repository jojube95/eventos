package com.example.eventos.eventoEmpleado;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.empleado.Empleado;
import com.example.eventos.evento.Evento;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.persona.Persona;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import com.example.eventos.tipoEvento.TipoEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.*;

class EventoEmpleadoTest {
    Evento evento;
    Empleado empleado;
    EventoEmpleado eventoEmpleadoNoId;
    EventoEmpleado eventoEmpleadoWithId;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();

        evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo"), true, true, true);

        eventoEmpleadoNoId = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        eventoEmpleadoWithId = new EventoEmpleado("id", evento, empleado, empleado.getTipo(), true, 0);
    }

    @Test
    void constructorNoIdTest(){
        assertEquals(evento, eventoEmpleadoWithId.getEvento());
        assertEquals(empleado, eventoEmpleadoWithId.getEmpleado());
        assertTrue(eventoEmpleadoWithId.isConfirmado());
        assertEquals(0, eventoEmpleadoWithId.getHorasExtras());
    }

    @Test
    void constructorTest(){
        assertEquals("id", eventoEmpleadoWithId.getId());
        assertEquals(evento, eventoEmpleadoWithId.getEvento());
        assertEquals(empleado, eventoEmpleadoWithId.getEmpleado());
        assertTrue(eventoEmpleadoWithId.isConfirmado());
        assertEquals(0, eventoEmpleadoWithId.getHorasExtras());
    }

    @Test
    void toStringTest(){
        assertEquals("EventoEmpleado{id='null', evento=Personas: 50\n" +
                "Localidad: Olleria\n" +
                "Confirmada: Sí, empleado=Empleado{id='idEmpleado1', tipo=TipoEmpleado{value='camarero'}, persona=Persona{nombre='nombre1', telefono='666777888', correo='correo'}, fijo=true, activo=true, devantal=true}, confirmado=true, horasExtras=0.0}", eventoEmpleadoNoId.toString());
        assertEquals("EventoEmpleado{id='id', evento=Personas: 50\n" +
                "Localidad: Olleria\n" +
                "Confirmada: Sí, empleado=Empleado{id='idEmpleado1', tipo=TipoEmpleado{value='camarero'}, persona=Persona{nombre='nombre1', telefono='666777888', correo='correo'}, fijo=true, activo=true, devantal=true}, confirmado=true, horasExtras=0.0}", eventoEmpleadoWithId.toString());
    }

    @Test
    void equalsTestFalseEvento(){
        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true, true);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        eventoEmpleado1.setEvento(new Evento("idEvento2", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion")));

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseEmpleado(){
        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true, true);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        eventoEmpleado1.setEmpleado(new Empleado("idEmpleado2", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true, true));

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseConfirmado(){
        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true, true);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        eventoEmpleado1.setConfirmado(false);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseHorasExtra(){
        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true, true);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        eventoEmpleado1.setHorasExtras(1);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }


    @Test
    void equalsTestTrue(){
        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true, true);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);

        assertEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestNull(){
        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true, true);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);

        assertNotEquals(null, eventoEmpleado1);
    }
}
