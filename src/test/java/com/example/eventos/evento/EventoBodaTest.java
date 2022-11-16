package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEvento.TipoEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventoBodaTest {
    EventoBoda eventoBoda;
    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        eventoBoda = new EventoBoda("id", new TipoEvento("boda"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Boda-Comida", "Sala1", new Distribucion("Distribucion"));
    }

    @Test
    void constructorTest(){
        assertEquals("id", eventoBoda.getId());
        assertEquals(new TipoEvento("boda"), eventoBoda.getTipo());
        assertEquals(new HorarioEvento("comida"), eventoBoda.getHorario());
        assertEquals(new Personas(50, 15), eventoBoda.getPersonas());
        assertEquals("Olleria", eventoBoda.getLocalidad());
        assertEquals(new GregorianCalendar(2022, Calendar.JULY, 25).getTime(), eventoBoda.getFecha());
        assertEquals(80, eventoBoda.getPrecioMenu());
        assertEquals(15, eventoBoda.getPrecioMenuNinyos());
        assertTrue(eventoBoda.isConfirmado());
        assertEquals("Boda-Comida", eventoBoda.getTitulo());
        assertEquals("Sala1", eventoBoda.getSala());
        assertEquals(new Distribucion("Distribucion"), eventoBoda.getDistribucion());
    }

    @Test
    void getCamarerosRecomendadosTest(){
        assertEquals(6, eventoBoda.getCamarerosRecomendados(10));
        assertEquals(5, eventoBoda.getCamarerosRecomendados(13));
        assertEquals(4, eventoBoda.getCamarerosRecomendados(15));
        assertEquals(4, eventoBoda.getCamarerosRecomendados(17));
    }
}
