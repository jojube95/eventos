package com.example.eventos.invitado;

import com.example.eventos.personas.Personas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvitadoMayorTest {
    InvitadoMayor invitadoMayor;

    @BeforeEach
    public void initEach(){
        invitadoMayor = new InvitadoMayor("id", "eventoId", "mesaId", "nombre", "mayor", "descripcion");
    }

    @Test
    void constructorTest(){
        assertEquals("id", invitadoMayor.getId());
        assertEquals("eventoId", invitadoMayor.getEventoId());
        assertEquals("mesaId", invitadoMayor.getMesaId());
        assertEquals("nombre", invitadoMayor.getNombre());
        assertEquals("mayor", invitadoMayor.getTipo());
        assertEquals("descripcion", invitadoMayor.getDescripcion());
    }

    @Test
    void generateTextoListadoTest(){
        assertEquals("nombre", invitadoMayor.generateTextoListado());
    }

    @Test
    void incrementPersonasTest(){
        Personas personasExpected = new Personas(10, 2);
        assertEquals(personasExpected, invitadoMayor.incrementPersonas(new Personas(9, 2)));
    }
}
