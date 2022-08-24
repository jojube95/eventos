package com.example.eventos.invitado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InvitadoTest {
    Invitado invitado;

    @BeforeEach
    public void initEach(){
        invitado = new Invitado("id", "idEvento", "idMesa", "nombre", "descripcion");
    }

    @Test
    void constructorTest(){
        assertEquals("id", invitado.getId());
        assertEquals("idEvento", invitado.getIdEvento());
        assertEquals("idMesa", invitado.getIdMesa());
        assertEquals("nombre", invitado.getNombre());
        assertEquals("descripcion", invitado.getDescripcion());
    }
}
