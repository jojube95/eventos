package com.example.eventos.personas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PersonasTest {

    Personas personas;

    @BeforeEach
    public void initEach(){
        personas = new Personas(10, 2);
    }

    @Test
    void constructorTest(){
        assertEquals(10, personas.getMayores());
        assertEquals(2, personas.getNinyos());
    }

    @Test
    void equalsTestTrue(){
        Personas personasTest = new Personas(10, 2);
        assertEquals(personas, personasTest);
    }

    @Test
    void equalsTestFalse(){
        Personas personasTest = new Personas(10, 3);
        assertNotEquals(personas, personasTest);
    }

    @Test
    void toStringTest(){
        assertEquals("10p, 2x", personas.toString());
    }
}
