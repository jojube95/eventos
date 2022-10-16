package com.example.eventos.eventoEmpleado;

import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventoEmpleadoTest {
    EventoEmpleado eventoEmpleadoNoId;
    EventoEmpleado eventoEmpleadoWithId;

    @BeforeEach
    public void initEach(){
        eventoEmpleadoNoId = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleadoWithId = new EventoEmpleado("id", "eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
    }

    @Test
    void constructorNoIdTest(){
        assertEquals("eventoId", eventoEmpleadoNoId.getEventoId());
        assertEquals("empleadoId", eventoEmpleadoNoId.getEmpleadoId());
        assertEquals(new TipoEmpleado("camarero"), eventoEmpleadoNoId.getTipo());
        assertEquals("nombre", eventoEmpleadoNoId.getNombre());
        assertTrue(eventoEmpleadoNoId.isFijo());
        assertTrue(eventoEmpleadoNoId.isConfirmado());
        assertEquals(0, eventoEmpleadoNoId.getHorasExtras());
    }

    @Test
    void constructorTest(){
        assertEquals("id", eventoEmpleadoWithId.getId());
        assertEquals("eventoId", eventoEmpleadoWithId.getEventoId());
        assertEquals("empleadoId", eventoEmpleadoWithId.getEmpleadoId());
        assertEquals(new TipoEmpleado("camarero"), eventoEmpleadoWithId.getTipo());
        assertEquals("nombre", eventoEmpleadoWithId.getNombre());
        assertTrue(eventoEmpleadoWithId.isFijo());
        assertTrue(eventoEmpleadoWithId.isConfirmado());
        assertEquals(0, eventoEmpleadoWithId.getHorasExtras());
    }

    @Test
    void toStringTest(){
        assertEquals("EventoEmpleado{id='null', eventoId='eventoId', empleadoId='empleadoId', tipo='tipo', nombre='nombre', fijo=true, confirmado=true, horasExtras=0.0}", eventoEmpleadoNoId.toString());
        assertEquals("EventoEmpleado{id='id', eventoId='eventoId', empleadoId='empleadoId', tipo='tipo', nombre='nombre', fijo=true, confirmado=true, horasExtras=0.0}", eventoEmpleadoWithId.toString());
    }

    @Test
    void equalsTestFalseEventoId(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleado1.setEventoId("eventoId2");

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseEmpleadoId(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleado1.setEmpleadoId("empleadoId2");

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseTipo(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleado1.setTipo(new TipoEmpleado("cocinero"));

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseNombre(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleado1.setNombre("nombre2");

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseFijo(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleado1.setFijo(false);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseConfirmado(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleado1.setConfirmado(false);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestFalseHorasExtra(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        eventoEmpleado1.setHorasExtras(1);

        assertNotEquals(eventoEmpleado1, eventoEmpleado2);
    }


    @Test
    void equalsTestTrue(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);

        assertEquals(eventoEmpleado1, eventoEmpleado2);
    }

    @Test
    void equalsTestNull(){
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);

        assertNotEquals(null, eventoEmpleado1);
    }
}
