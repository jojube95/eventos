package com.example.eventos.mesa;

import com.example.eventos.personas.Personas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MesaTest {
    MesaReserva mesa1;
    Mesa mesa2;
    Mesa mesa3;
    MesaReserva mesa4;
    Mesa mesaExcel1;
    Mesa mesaExcel2;
    Mesa mesaExcel3;

    @BeforeEach
    public void initEach(){
        mesa1 = new MesaReserva("id", "eventoId", new Personas(8, 1), 2, "descripcion", "representante", false);

        mesa2 = new Mesa("eventoId", new Personas(8, 1), 2, "descripcion");
        mesa3 = new Mesa("mesaId", "eventoId", new Personas(8, 1), 2, "descripcion");
        mesa4 = new MesaReserva("id", "eventoId", new Personas(8, 1), 2, "descripcion", "representante", true);

        mesaExcel1 = new Mesa("Taula 1-Botellas", "eventoId", new Personas(8, 2));
        mesaExcel2 = new Mesa("Mesa 3 - Licores ", "eventoId", new Personas(8, 2));
        mesaExcel3 = new Mesa("Mesa 7  ", "eventoId", new Personas(8, 2));
    }

    @Test
    void constructor1Test(){
        assertEquals("eventoId", mesa1.getEventoId());
        assertEquals("representante", mesa1.getRepresentante());
        assertEquals(8, mesa1.getPersonas().getMayores());
        assertEquals(2, mesa1.getNumero());
        assertFalse(mesa1.isPagado());
    }

    @Test
    void constructor2Test(){
        assertEquals("eventoId", mesa2.getEventoId());
        assertEquals(8, mesa2.getPersonas().getMayores());
        assertEquals(2, mesa2.getNumero());
    }

    @Test
    void constructor3Test(){
        assertEquals("mesaId", mesa3.getId());
        assertEquals("eventoId", mesa3.getEventoId());
        assertEquals(8, mesa3.getPersonas().getMayores());
        assertEquals(2, mesa3.getNumero());
    }

    @Test
    void constructor4Test(){
        assertEquals("id", mesa4.getId());
        assertEquals("eventoId", mesa4.getEventoId());
        assertEquals("representante", mesa4.getRepresentante());
        assertEquals(8, mesa4.getPersonas().getMayores());
        assertEquals(2, mesa4.getNumero());
        assertTrue(mesa4.isPagado());
    }

    @Test
    void constructorExcel1Test(){
        assertEquals("eventoId", mesaExcel1.getEventoId());
        assertEquals(1, mesaExcel1.getNumero());
        assertEquals(8, mesaExcel1.getPersonas().getMayores());
        assertEquals(2, mesaExcel1.getPersonas().getNinyos());
        assertEquals("Botellas", mesaExcel1.getDescripcion());
    }

    @Test
    void constructorExcel2Test(){
        assertEquals("eventoId", mesaExcel2.getEventoId());
        assertEquals(3, mesaExcel2.getNumero());
        assertEquals(8, mesaExcel2.getPersonas().getMayores());
        assertEquals(2, mesaExcel2.getPersonas().getNinyos());
        assertEquals("Licores", mesaExcel2.getDescripcion());
    }

    @Test
    void constructorExcel3Test(){
        assertEquals("eventoId", mesaExcel3.getEventoId());
        assertEquals(7, mesaExcel3.getNumero());
        assertEquals(8, mesaExcel3.getPersonas().getMayores());
        assertEquals(2, mesaExcel3.getPersonas().getNinyos());
        assertEquals("", mesaExcel3.getDescripcion());
    }

    @Test
    void equalsTestTrue(){
        MesaReserva mesa5 = new MesaReserva("id", "eventoId", new Personas(8, 1), 2, "descripcion", "representante", true);

        assertEquals(mesa4, mesa5);
    }

    @Test
    void equalsTestFalse(){
        MesaReserva mesa5 = new MesaReserva("id", "eventoId", new Personas(9, 1), 2, "descripcion", "representante", true);

        assertNotEquals(mesa4, mesa5);
    }

    @Test
    void generatePhraseWithoutDescriptionTest(){
        Phrase generatedPhrase = mesaExcel3.generatePhrase();

        assertEquals("Mesa 7. 8p, 2x", generatedPhrase.getContent());
        assertNull(generatedPhrase.getFont().getColor());
        assertEquals(12, generatedPhrase.getFont().getSize());
        assertEquals(Font.FontFamily.HELVETICA, generatedPhrase.getFont().getFamily());
    }

    @Test
    void generatePhraseWithDescriptionTest(){
        Phrase generatedPhrase = mesa2.generatePhrase();

        assertEquals("Mesa 2. 8p, 1x. Descripción: descripcion", generatedPhrase.getContent());
        assertEquals(BaseColor.RED, generatedPhrase.getFont().getColor());
        assertEquals(12, generatedPhrase.getFont().getSize());
        assertEquals(Font.FontFamily.HELVETICA, generatedPhrase.getFont().getFamily());
    }

    @Test
    void incrementPersonasTest(){
        Personas expectedPersonas = new Personas(8, 1);

        assertEquals(expectedPersonas, mesa1.incrementPersonas(new Personas(0, 0)));
    }

    @Test
    void toStringTest(){
        assertEquals("Mesa 2. 8p, 1x. Descripción: descripcion", mesa2.toString());
        assertEquals("Mesa 2. 8p, 1x. Descripción: descripcion", mesa3.toString());
        assertEquals("Mesa 1. 8p, 2x. Descripción: Botellas", mesaExcel1.toString());
        assertEquals("Mesa 3. 8p, 2x. Descripción: Licores", mesaExcel2.toString());
        assertEquals("Mesa 7. 8p, 2x", mesaExcel3.toString());
    }
}
