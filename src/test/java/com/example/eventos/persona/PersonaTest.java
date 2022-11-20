package com.example.eventos.persona;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PersonaTest {
    Persona persona;

    @BeforeEach
    public void initEach(){
        persona = new Persona("nombre", "666777888", "correo@correo.com");
    }

    @Test
    void constructorTest(){
        assertEquals("nombre", persona.getNombre());
        assertEquals("666777888", persona.getTelefono());
        assertEquals("correo@correo.com", persona.getCorreo());
    }

    @Test
    void equalsTestTrue(){
        Persona personaTest = new Persona("nombre", "666777888", "correo@correo.com");
        assertEquals(persona, personaTest);
    }

    @Test
    void equalsTestFalse(){
        Persona personaTest = new Persona("nombre1", "666777888", "correo@correo.com");
        assertNotEquals(persona, personaTest);
    }

    @Test
    void toStringTest(){
        assertEquals("Persona{nombre='nombre', telefono='666777888', correo='correo@correo.com'}", persona.toString());
    }
}
