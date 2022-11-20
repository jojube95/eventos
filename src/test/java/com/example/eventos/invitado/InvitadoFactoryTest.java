package com.example.eventos.invitado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InvitadoFactoryTest {
    Invitado invitadoMayor;
    Invitado InvitadoNinyo;

    @BeforeEach
    public void initEach(){
        invitadoMayor = new InvitadoMayor("id", "eventoId", "mesaId", "nombre", "Mayor", "descripcion");
        InvitadoNinyo = new InvitadoNinyo("id", "eventoId", "mesaId", "nombre", "Niño", "descripcion");
    }

    @Test
    void crearInvitadoTest() {
        assertEquals("InvitadoMayor", InvitadoFactory.crearInvitado(invitadoMayor.getId(), invitadoMayor.getEventoId(), invitadoMayor.getMesaId(), invitadoMayor.getNombre(), invitadoMayor.getTipo(), invitadoMayor.getDescripcion()).getClass().getSimpleName());
        assertEquals("InvitadoNinyo", InvitadoFactory.crearInvitado(InvitadoNinyo.getId(), InvitadoNinyo.getEventoId(), InvitadoNinyo.getMesaId(), InvitadoNinyo.getNombre(), InvitadoNinyo.getTipo(), InvitadoNinyo.getDescripcion()).getClass().getSimpleName());
    }

    @Test
    void crearInvitadoFromTextExcel1() {
        Invitado invitado = InvitadoFactory.crearInvitadoFromTextExcel("Pepe", "eventoId");
        assertEquals("InvitadoMayor", invitado.getClass().getSimpleName());
        assertEquals("eventoId", invitado.getEventoId());
        assertEquals("Pepe", invitado.getNombre());
        assertEquals("Mayor", invitado.getTipo());
        assertEquals("", invitado.getDescripcion());
    }

    @Test
    void crearInvitadoFromTextExcel2() {
        Invitado invitado = InvitadoFactory.crearInvitadoFromTextExcel(" Pepe - x", "eventoId");
        assertEquals("InvitadoNinyo", invitado.getClass().getSimpleName());
        assertEquals("eventoId", invitado.getEventoId());
        assertEquals("Pepe", invitado.getNombre());
        assertEquals("Niño", invitado.getTipo());
        assertEquals("", invitado.getDescripcion());
    }

    @Test
    void crearInvitadoFromTextExcel3() {
        Invitado invitado = InvitadoFactory.crearInvitadoFromTextExcel(" Pepe - celiaco", "eventoId");
        assertEquals("InvitadoMayor", invitado.getClass().getSimpleName());
        assertEquals("eventoId", invitado.getEventoId());
        assertEquals("Pepe", invitado.getNombre());
        assertEquals("Mayor", invitado.getTipo());
        assertEquals("celiaco", invitado.getDescripcion());
    }

    @Test
    void crearInvitadoFromTextExcel4() {
        Invitado invitado = InvitadoFactory.crearInvitadoFromTextExcel("Pepe -x- Celiaco ", "eventoId2");
        assertEquals("InvitadoNinyo", invitado.getClass().getSimpleName());
        assertEquals("eventoId2", invitado.getEventoId());
        assertEquals("Pepe", invitado.getNombre());
        assertEquals("Niño", invitado.getTipo());
        assertEquals("Celiaco", invitado.getDescripcion());
    }


}
