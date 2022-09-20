package com.example.eventos.mesa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MesaTest {
    Mesa mesa1;
    Mesa mesa2;
    Mesa mesa3;
    Mesa mesa4;
    Mesa mesaExcel1;
    Mesa mesaExcel2;
    Mesa mesaExcel3;

    @BeforeEach
    public void initEach(){
        mesa1 = new Mesa("idEvento", "representante",8, 1, 2, false, "descripcion");
        mesa2 = new Mesa("idEvento", 8, 1, 2, "descripcion");
        mesa3 = new Mesa("idMesa", "idEvento", 8, 1, 2, "descripcion");
        mesa4 = new Mesa("idMesa", "idEvento", "representante", 8, 1, 2, true, "descripcion");
        mesaExcel1 = new Mesa("Taula 1-Botellas", "idEvento", 8, 2);
        mesaExcel2 = new Mesa("Mesa 3 - Licores ", "idEvento", 8, 2);
        mesaExcel3 = new Mesa("Mesa 7  ", "idEvento", 8, 2);
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
    void constructorExcel1Test(){
        assertEquals("idEvento", mesaExcel1.getIdEvento());
        assertEquals(1, mesaExcel1.getNumero());
        assertEquals(8, mesaExcel1.getPersonas());
        assertEquals(2, mesaExcel1.getNinyos());
        assertEquals("Botellas", mesaExcel1.getDescripcion());
    }

    @Test
    void constructorExcel2Test(){
        assertEquals("idEvento", mesaExcel2.getIdEvento());
        assertEquals(3, mesaExcel2.getNumero());
        assertEquals(8, mesaExcel2.getPersonas());
        assertEquals(2, mesaExcel2.getNinyos());
        assertEquals("Licores", mesaExcel2.getDescripcion());
    }

    @Test
    void constructorExcel3Test(){
        assertEquals("idEvento", mesaExcel3.getIdEvento());
        assertEquals(7, mesaExcel3.getNumero());
        assertEquals(8, mesaExcel3.getPersonas());
        assertEquals(2, mesaExcel3.getNinyos());
        assertEquals("", mesaExcel3.getDescripcion());
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
