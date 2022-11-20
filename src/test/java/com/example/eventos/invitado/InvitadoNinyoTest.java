package com.example.eventos.invitado;

import com.example.eventos.personas.Personas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvitadoNinyoTest {
    InvitadoNinyo invitadoNinyo;

    @BeforeEach
    public void initEach(){
        invitadoNinyo = new InvitadoNinyo("id", "eventoId", "mesaId", "nombre", "ninyo", "descripcion");
    }

    @Test
    void constructorTest(){
        assertEquals("id", invitadoNinyo.getId());
        assertEquals("eventoId", invitadoNinyo.getEventoId());
        assertEquals("mesaId", invitadoNinyo.getMesaId());
        assertEquals("nombre", invitadoNinyo.getNombre());
        assertEquals("ninyo", invitadoNinyo.getTipo());
        assertEquals("descripcion", invitadoNinyo.getDescripcion());
    }

    @Test
    void generateTextoListadoTest(){
        assertEquals("nombre-x", invitadoNinyo.generateTextoListado());
    }

    @Test
    void incrementPersonasTest(){
        Personas personasExpected = new Personas(9, 3);
        assertEquals(personasExpected, invitadoNinyo.incrementPersonas(new Personas(9, 2)));
    }
}
