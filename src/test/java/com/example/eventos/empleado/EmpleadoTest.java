package com.example.eventos.empleado;

import com.example.eventos.evento.Evento;
import com.example.eventos.protagonista.Protagonista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTest {
    Empleado empleadoNoId;
    Empleado empleadoWithId;

    @BeforeEach
    public void initEach(){
        empleadoNoId = new Empleado("tipo", "nombre", "telefono", true);
        empleadoWithId = new Empleado("id", "tipo", "nombre", "telefono", true);
    }

    @Test
    void constructorNoIdTest(){
        assertEquals("tipo", empleadoNoId.getTipo());
        assertEquals("nombre", empleadoNoId.getNombre());
        assertEquals("telefono", empleadoNoId.getTelefono());
        assertTrue(empleadoNoId.isFijo());
    }

    @Test
    void constructorTest(){
        assertEquals("id", empleadoWithId.getId());
        assertEquals("tipo", empleadoWithId.getTipo());
        assertEquals("nombre", empleadoWithId.getNombre());
        assertEquals("telefono", empleadoWithId.getTelefono());
        assertTrue(empleadoWithId.isFijo());
    }

    @Test
    void toStringTest(){
        assertEquals("Empleado{id='null', tipo='tipo', nombre='nombre', telefono='telefono', fijo=true}", empleadoNoId.toString());
        assertEquals("Empleado{id='id', tipo='tipo', nombre='nombre', telefono='telefono', fijo=true}", empleadoWithId.toString());
    }

    @Test
    void equalsTestFalseTipo(){
        Empleado empleado1 = new Empleado("tipo", "nombre", "telefono", true);
        Empleado empleado2 = new Empleado("tipo", "nombre", "telefono", true);
        empleado1.setTipo("tipo2");

        assertNotEquals(empleado1, empleado2);
    }

    @Test
    void equalsTestFalseNombre(){
        Empleado empleado1 = new Empleado("tipo", "nombre", "telefono", true);
        Empleado empleado2 = new Empleado("tipo", "nombre", "telefono", true);
        empleado1.setNombre("nombre2");

        assertNotEquals(empleado1, empleado2);
    }

    @Test
    void equalsTestFalseTelefono(){
        Empleado empleado1 = new Empleado("tipo", "nombre", "telefono", true);
        Empleado empleado2 = new Empleado("tipo", "nombre", "telefono", true);
        empleado1.setTelefono("telefono2");

        assertNotEquals(empleado1, empleado2);
    }

    @Test
    void equalsTestFalseFijo(){
        Empleado empleado1 = new Empleado("tipo", "nombre", "telefono", true);
        Empleado empleado2 = new Empleado("tipo", "nombre", "telefono", true);
        empleado1.setFijo(false);

        assertNotEquals(empleado1, empleado2);
    }


    @Test
    void equalsTestTrue(){
        Empleado empleado1 = new Empleado("tipo", "nombre", "telefono", true);
        Empleado empleado2 = new Empleado("tipo", "nombre", "telefono", true);

        assertEquals(empleado1, empleado2);
    }

    @Test
    void equalsTestNull(){
        Empleado empleado1 = new Empleado("tipo", "nombre", "telefono", true);

        assertNotEquals(empleado1, null);
    }
}
