package com.example.eventos.mesa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MesaTest {
    Mesa mesa1;
    Mesa mesa2;
    Mesa mesa3;
    Mesa mesa4;

    @BeforeEach
    public void initEach(){
        mesa1 = new Mesa("idEvento", "representante",8, 1, 2, false, "descripcion");
        mesa2 = new Mesa("idEvento", 8, 1, 2, "descripcion");
        mesa3 = new Mesa("idMesa", "idEvento", 8, 1, 2, "descripcion");
        mesa4 = new Mesa("idMesa", "idEvento", "representante", 8, 1, 2, true, "descripcion");
    }

    @Test
    void constructor1Test(){
        assertEquals("idEvento", mesa1.getIdEvento());
        assertEquals("representante", mesa1.getRepresentante());
        assertEquals(8, mesa1.getPersonas());
        assertEquals(2, mesa1.getNumero());
        assertFalse(mesa1.isPagado());
    }

    @Test
    void constructor2Test(){
        assertEquals("idEvento", mesa2.getIdEvento());
        assertEquals(8, mesa2.getPersonas());
        assertEquals(2, mesa2.getNumero());
    }

    @Test
    void constructor3Test(){
        assertEquals("idMesa", mesa3.getId());
        assertEquals("idEvento", mesa3.getIdEvento());
        assertEquals(8, mesa3.getPersonas());
        assertEquals(2, mesa3.getNumero());
    }

    @Test
    void constructor4Test(){
        assertEquals("idMesa", mesa4.getId());
        assertEquals("idEvento", mesa4.getIdEvento());
        assertEquals("representante", mesa4.getRepresentante());
        assertEquals(8, mesa4.getPersonas());
        assertEquals(2, mesa4.getNumero());
        assertTrue(mesa4.isPagado());
    }

    @Test
    void equalsTestTrue(){
        Mesa mesa5 = new Mesa("idMesa", "idEvento", "representante", 8, 1, 2, true, "descripcion");
        assertEquals(mesa4, mesa5);
    }

    @Test
    void equalsTestFalse(){
        Mesa mesa5 = new Mesa("idMesa", "idEvento", "representante", 9, 1, 2, true, "descripcion");
        assertNotEquals(mesa4, mesa5);
    }
}
