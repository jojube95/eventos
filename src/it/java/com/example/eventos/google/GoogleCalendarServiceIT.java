package com.example.eventos.google;

import com.example.eventos.evento.Evento;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEvento.TipoEvento;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GoogleCalendarServiceIT {
    private final GoogleCalendarService googleCalendarService = new GoogleCalendarService("5vtcks38679juuk2s0v86c37t4@group.calendar.google.com");
    Evento evento;

    @BeforeEach
    public void initEach(){
        Date fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        evento = new Evento(new TipoEvento("boda"), new HorarioEvento("comida"), new Personas(150, 10), "Benetuser", fecha, "Boda-Comida", "Sala1");
        evento.setDescripcion("Descripción");
        String eventoId = this.googleCalendarService.add(evento);
        evento.setId(eventoId);
    }

    @Test
    void eventoIsAddedToGoogleCalendar() throws IOException {
        Event eventoCalendar = this.googleCalendarService.getEventById(evento.getId());

        assertEquals("Boda-Comida", eventoCalendar.getSummary());
        assertEquals("Descripción: Descripción\nPersonas: 150\nLocalidad: Benetuser\nConfirmada: No", eventoCalendar.getDescription());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-25")), eventoCalendar.getStart());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-26")), eventoCalendar.getEnd());
    }

    @Test
    void eventoIsUpdatedInGoogleCalendar() throws IOException {
        evento.setTitulo("Comunión-Cena");
        evento.setDescripcion("Descripción2");
        evento.setPersonas(new Personas(99, 0));
        evento.setLocalidad("Olleria");
        evento.setConfirmado(true);
        evento.setFecha(new GregorianCalendar(2022, Calendar.JULY, 28).getTime());

        this.googleCalendarService.update(evento);

        Event eventoCalendar = this.googleCalendarService.getEventById(evento.getId());

        assertEquals("Comunión-Cena", eventoCalendar.getSummary());
        assertEquals("Descripción: Descripción2\nPersonas: 99\nLocalidad: Olleria\nConfirmada: Sí", eventoCalendar.getDescription());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-28")), eventoCalendar.getStart());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-29")), eventoCalendar.getEnd());
    }

    @Test
    void eventoIsDeletedInGoogleCalendar() throws IOException {
        this.googleCalendarService.delete(evento);

        Event eventoCalendar = this.googleCalendarService.getEventById(evento.getId());

        assertEquals("cancelled", eventoCalendar.getStatus());
    }

    @AfterEach
    public void clearEventoFromCalendar() {
        this.googleCalendarService.clearEventById(evento.getId());
    }
}