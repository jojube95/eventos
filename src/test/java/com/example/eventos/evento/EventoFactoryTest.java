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

class EventoFactoryTest {
    Evento eventoBoda = new Evento();
    Evento eventoComunion = new Evento();
    Evento eventoIndividual = new Evento();
    Evento eventoComunal = new Evento();
    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        eventoBoda = new Evento("id", new TipoEvento("boda"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Boda-Comida", "Sala1", new Distribucion("Distribucion"));
        eventoComunion = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Boda-Comida", "Sala1", new Distribucion("Distribucion"));
        eventoIndividual = new Evento("id", new TipoEvento("eventoIndividual"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Boda-Comida", "Sala1", new Distribucion("Distribucion"));
        eventoComunal = new Evento("id", new TipoEvento("eventoComunal"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Boda-Comida", "Sala1", new Distribucion("Distribucion"));
    }

    @Test
    void crearEventoTest() {
        assertEquals("EventoBoda", EventoFactory.crearEvento(eventoBoda).getClass().getSimpleName());
        assertEquals("Evento", EventoFactory.crearEvento(eventoComunion).getClass().getSimpleName());
        assertEquals("EventoIndividual", EventoFactory.crearEvento(eventoIndividual).getClass().getSimpleName());
        assertEquals("Evento", EventoFactory.crearEvento(eventoComunal).getClass().getSimpleName());
    }
}
