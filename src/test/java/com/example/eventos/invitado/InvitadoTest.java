package com.example.eventos.invitado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InvitadoTest {
    Invitado invitado;
    Invitado invitadoFromExcel1;
    Invitado invitadoFromExcel2;
    Invitado invitadoFromExcel3;
    Invitado invitadoFromExcel4;

    @BeforeEach
    public void initEach(){
        invitado = new Invitado("id", "idEvento", "idMesa", "nombre", "Mayor", "descripcion");
        invitadoFromExcel1 = new Invitado("Pepe-Intolerant", "idEvento");
        invitadoFromExcel2 = new Invitado("Amaia-x-Celiaca ", "idEvento");
        invitadoFromExcel3 = new Invitado("Antonio -Celiaco- x ", "idEvento");
        invitadoFromExcel4 = new Invitado("Pepe", "idEvento");
    }

    @Test
    void constructorTest(){
        assertEquals("id", invitado.getId());
        assertEquals("idEvento", invitado.getIdEvento());
        assertEquals("idMesa", invitado.getIdMesa());
        assertEquals("nombre", invitado.getNombre());
        assertEquals("descripcion", invitado.getDescripcion());
    }

    @Test
    void constructorFromExcel1Test(){
        assertEquals("idEvento", invitadoFromExcel1.getIdEvento());
        assertEquals("Pepe", invitadoFromExcel1.getNombre());
        assertEquals("Mayor", invitadoFromExcel1.getTipo());
        assertEquals("Intolerant", invitadoFromExcel1.getDescripcion());
    }

    @Test
    void constructorFromExcel2Test(){
        assertEquals("idEvento", invitadoFromExcel2.getIdEvento());
        assertEquals("Amaia", invitadoFromExcel2.getNombre());
        assertEquals("Niño", invitadoFromExcel2.getTipo());
        assertEquals("Celiaca", invitadoFromExcel2.getDescripcion());
    }

    @Test
    void constructorFromExcel3Test(){
        assertEquals("idEvento", invitadoFromExcel3.getIdEvento());
        assertEquals("Antonio", invitadoFromExcel3.getNombre());
        assertEquals("Niño", invitadoFromExcel3.getTipo());
        assertEquals("Celiaco", invitadoFromExcel3.getDescripcion());
    }

    @Test
    void constructorFromExcel4Test(){
        assertEquals("idEvento", invitadoFromExcel4.getIdEvento());
        assertEquals("Pepe", invitadoFromExcel4.getNombre());
        assertEquals("Mayor", invitadoFromExcel4.getTipo());
        assertEquals("", invitadoFromExcel4.getDescripcion());
    }
}
