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
        invitado = InvitadoFactory.crearInvitado("id", "eventoId", "mesaId", "nombre", "Mayor", "descripcion");
        invitadoFromExcel1 = InvitadoFactory.crearInvitadoFromTextExcel("Pepe-Intolerant", "eventoId");
        invitadoFromExcel2 = InvitadoFactory.crearInvitadoFromTextExcel("Amaia-x-Celiaca ", "eventoId");
        invitadoFromExcel3 = InvitadoFactory.crearInvitadoFromTextExcel("Antonio -Celiaco- x ", "eventoId");
        invitadoFromExcel4 = InvitadoFactory.crearInvitadoFromTextExcel("Pepe", "eventoId");
    }

    @Test
    void constructorTest(){
        assertEquals("id", invitado.getId());
        assertEquals("eventoId", invitado.getEventoId());
        assertEquals("mesaId", invitado.getMesaId());
        assertEquals("nombre", invitado.getNombre());
        assertEquals("descripcion", invitado.getDescripcion());
    }

    @Test
    void constructorFromExcel1Test(){
        assertEquals("eventoId", invitadoFromExcel1.getEventoId());
        assertEquals("Pepe", invitadoFromExcel1.getNombre());
        assertEquals("Mayor", invitadoFromExcel1.getTipo());
        assertEquals("Intolerant", invitadoFromExcel1.getDescripcion());
    }

    @Test
    void constructorFromExcel2Test(){
        assertEquals("eventoId", invitadoFromExcel2.getEventoId());
        assertEquals("Amaia", invitadoFromExcel2.getNombre());
        assertEquals("Niño", invitadoFromExcel2.getTipo());
        assertEquals("Celiaca", invitadoFromExcel2.getDescripcion());
    }

    @Test
    void constructorFromExcel3Test(){
        assertEquals("eventoId", invitadoFromExcel3.getEventoId());
        assertEquals("Antonio", invitadoFromExcel3.getNombre());
        assertEquals("Niño", invitadoFromExcel3.getTipo());
        assertEquals("Celiaco", invitadoFromExcel3.getDescripcion());
    }

    @Test
    void constructorFromExcel4Test(){
        assertEquals("eventoId", invitadoFromExcel4.getEventoId());
        assertEquals("Pepe", invitadoFromExcel4.getNombre());
        assertEquals("Mayor", invitadoFromExcel4.getTipo());
        assertEquals("", invitadoFromExcel4.getDescripcion());
    }
}
