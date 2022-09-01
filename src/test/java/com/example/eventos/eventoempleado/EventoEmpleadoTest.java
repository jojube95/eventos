package com.example.eventos.eventoempleado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventoEmpleadoTest {
    EventoEmpleado eventoEmpleadoNoId;
    EventoEmpleado eventoEmpleadoWithId;

    @BeforeEach
    public void initEach(){
        eventoEmpleadoNoId = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleadoWithId = new EventoEmpleado("id", "idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
    }

    @Test
    void constructorNoIdTest(){
        assertEquals("idEvento", eventoEmpleadoNoId.getIdEvento());
        assertEquals("idEmpleado", eventoEmpleadoNoId.getIdEmpleado());
        assertEquals("tipo", eventoEmpleadoNoId.getTipo());
        assertEquals("nombre", eventoEmpleadoNoId.getNombre());
        assertTrue(eventoEmpleadoNoId.isFijo());
        assertTrue(eventoEmpleadoNoId.isConfirmado());
        assertEquals(0, eventoEmpleadoNoId.getHorasExtras());
    }

    @Test
    void constructorTest(){
        assertEquals("id", eventoEmpleadoWithId.getId());
        assertEquals("idEvento", eventoEmpleadoWithId.getIdEvento());
        assertEquals("idEmpleado", eventoEmpleadoWithId.getIdEmpleado());
        assertEquals("tipo", eventoEmpleadoWithId.getTipo());
        assertEquals("nombre", eventoEmpleadoWithId.getNombre());
        assertTrue(eventoEmpleadoWithId.isFijo());
        assertTrue(eventoEmpleadoWithId.isConfirmado());
        assertEquals(0, eventoEmpleadoWithId.getHorasExtras());
    }

    @Test
    void toStringTest(){
        assertEquals("EventoEmpleado{id='null', idEvento='idEvento', idEmpleado='idEmpleado', tipo='tipo', nombre='nombre', fijo=true, confirmado=true, horasExtras=0.0}", eventoEmpleadoNoId.toString());
        assertEquals("EventoEmpleado{id='id', idEvento='idEvento', idEmpleado='idEmpleado', tipo='tipo', nombre='nombre', fijo=true, confirmado=true, horasExtras=0.0}", eventoEmpleadoWithId.toString());
    }

    @Test
    void equalsTestFalseIdEvento(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleado1.setIdEvento("idEvento2");

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseIdEmpleado(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleado1.setIdEmpleado("idEmpleado2");

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseTipo(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleado1.setTipo("tipo2");

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseNombre(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleado1.setNombre("nombre2");

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseFijo(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleado1.setFijo(false);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseConfirmado(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleado1.setConfirmado(false);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseHorasExtra(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        eventoEmpleado1.setHorasExtras(1);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }


    @Test
    void equalsTestTrue(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);

        assertEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestNull(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);

        assertNotEquals(null, eventoEmpleado1);
    }
}
