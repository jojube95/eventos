package com.example.eventos.mesa;

import com.example.eventos.personas.Personas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MesaReservaTest {
    MesaReserva mesa1;
    MesaReserva mesa2;

    @BeforeEach
    public void initEach(){
        mesa1 = new MesaReserva("id", "eventoId", new Personas(8, 1), 2, "descripcion", "representante", false);
        mesa2 = new MesaReserva("id", "eventoId", new Personas(9, 2), 1, "descripcion", "representante1", true);
    }

    @Test
    void constructor1Test(){
        assertEquals("eventoId", mesa1.getEventoId());
        assertEquals("representante", mesa1.getRepresentante());
        assertEquals(8, mesa1.getPersonas().getMayores());
        assertEquals(1, mesa1.getPersonas().getNinyos());
        assertEquals(2, mesa1.getNumero());
        assertFalse(mesa1.isPagado());
    }

    @Test
    void constructor2Test(){
        assertEquals("id", mesa2.getId());
        assertEquals("eventoId", mesa2.getEventoId());
        assertEquals("representante1", mesa2.getRepresentante());
        assertEquals(9, mesa2.getPersonas().getMayores());
        assertEquals(2, mesa2.getPersonas().getNinyos());
        assertEquals(1, mesa2.getNumero());
        assertTrue(mesa2.isPagado());
    }

    @Test
    void equalsTestTrue(){
        MesaReserva mesa3 = new MesaReserva("id", "eventoId", new Personas(8, 1), 2, "descripcion", "representante", false);

        assertEquals(mesa1, mesa3);
    }

    @Test
    void equalsTestFalse(){
        MesaReserva mesa3 = new MesaReserva("id", "eventoId", new Personas(8, 1), 2, "descripcion", "representante1", true);

        assertNotEquals(mesa1, mesa3);
    }

    @Test
    void toStringTest(){
        assertEquals("MesaReserva{representante='representante', pagado=false, id='id', eventoId='eventoId', personas=8p, 1x, numero=2, descripcion='descripcion'}", mesa1.toString());
        assertEquals("MesaReserva{representante='representante1', pagado=true, id='id', eventoId='eventoId', personas=9p, 2x, numero=1, descripcion='descripcion'}", mesa2.toString());
    }
}
