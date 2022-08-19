package com.example.eventos.protagonista;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProtagonistaTest {

    @Test
    void equalsTestTrue(){
        Protagonista protagonista1 = new Protagonista("Tipo", "Nombre", "666777888", "correo@correo.es");
        Protagonista protagonista2 = new Protagonista("Tipo", "Nombre", "666777888", "correo@correo.es");
        assertEquals(protagonista1, protagonista2);
    }

    @Test
    void equalsTestFalse(){
        Protagonista protagonista1 = new Protagonista("Tipo", "Nombre", "666777888", "correo@correo.es");
        Protagonista protagonista2 = new Protagonista("Tipo", "Nombre1", "666777888", "correo@correo.es");
        assertNotEquals(protagonista1, protagonista2);
    }
}
