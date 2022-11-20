package com.example.eventos.empleado;

import com.example.eventos.persona.Persona;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTest {
    Empleado empleadoNoId;
    Empleado empleadoWithId;

    @BeforeEach
    public void initEach(){
        empleadoNoId = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        empleadoWithId = new Empleado("id", new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
    }

    @Test
    void constructorNoIdTest(){
        assertEquals(new TipoEmpleado("camarero"), empleadoNoId.getTipo());
        assertEquals(new Persona("nombre", "telefono", "correo"), empleadoNoId.getPersona());
        assertTrue(empleadoNoId.isFijo());
    }

    @Test
    void constructorTest(){
        assertEquals("id", empleadoWithId.getId());
        assertEquals(new TipoEmpleado("camarero"), empleadoWithId.getTipo());
        assertEquals(new Persona("nombre", "telefono", "correo"), empleadoNoId.getPersona());
        assertTrue(empleadoWithId.isFijo());
    }

    @Test
    void toStringTest(){
        assertEquals("Empleado{id='null', tipo=TipoEmpleado{value='camarero'}, persona=Persona{nombre='nombre', telefono='telefono', correo='correo'}, fijo=true}", empleadoNoId.toString());
        assertEquals("Empleado{id='id', tipo=TipoEmpleado{value='camarero'}, persona=Persona{nombre='nombre', telefono='telefono', correo='correo'}, fijo=true}", empleadoWithId.toString());
    }

    @Test
    void equalsTestFalseTipo(){
        Empleado empleado1 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        Empleado empleado2 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        empleado1.setTipo(new TipoEmpleado("cocinero"));

        assertNotEquals(empleado1, empleado2);
    }

    @Test
    void equalsTestFalsePersona(){
        Empleado empleado1 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        Empleado empleado2 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        empleado1.setPersona(new Persona("nombre2", "telefono", "correo"));

        assertNotEquals(empleado1, empleado2);
    }

    @Test
    void equalsTestFalseFijo(){
        Empleado empleado1 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        Empleado empleado2 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        empleado1.setFijo(false);

        assertNotEquals(empleado1, empleado2);
    }


    @Test
    void equalsTestTrue(){
        Empleado empleado1 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);
        Empleado empleado2 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);

        assertEquals(empleado1, empleado2);
    }

    @Test
    void equalsTestNull(){
        Empleado empleado1 = new Empleado(new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true);

        assertNotEquals(null, empleado1);
    }
}
