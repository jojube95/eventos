package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEvento.TipoEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventoIndividualTest {
    EventoIndividual eventoIndividual;
    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        eventoIndividual = new EventoIndividual("id", new TipoEvento("individual"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Individual-Comida", "Sala1", new Distribucion("Distribucion"));
    }

    @Test
    void constructorTest(){
        assertEquals("id", eventoIndividual.getId());
        assertEquals(new TipoEvento("individual"), eventoIndividual.getTipo());
        assertEquals(new HorarioEvento("comida"), eventoIndividual.getHorario());
        assertEquals(new Personas(50, 15), eventoIndividual.getPersonas());
        assertEquals("Olleria", eventoIndividual.getLocalidad());
        assertEquals(new GregorianCalendar(2022, Calendar.JULY, 25).getTime(), eventoIndividual.getFecha());
        assertEquals(80, eventoIndividual.getPrecioMenu());
        assertEquals(15, eventoIndividual.getPrecioMenuNinyos());
        assertTrue(eventoIndividual.isConfirmado());
        assertEquals("Individual-Comida", eventoIndividual.getTitulo());
        assertEquals("Sala1", eventoIndividual.getSala());
        assertEquals(new Distribucion("Distribucion"), eventoIndividual.getDistribucion());
    }

    @Test
    void getCamarerosRecomendadosTest(){
        assertEquals(5, eventoIndividual.getCamarerosRecomendados(10));
        assertEquals(4, eventoIndividual.getCamarerosRecomendados(13));
        assertEquals(3, eventoIndividual.getCamarerosRecomendados(15));
        assertEquals(3, eventoIndividual.getCamarerosRecomendados(17));
    }

    @Test
    void isEventoWithMesasConReservaTest(){
        assertTrue(eventoIndividual.isEventoWithMesasConReserva());
    }
}
