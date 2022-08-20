package com.example.eventos.google;

import com.example.eventos.evento.Evento;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GoogleCalendarServiceIT {
    private final GoogleCalendarService googleCalendarService = new GoogleCalendarService("5vtcks38679juuk2s0v86c37t4@group.calendar.google.com");
    Evento evento;

    @BeforeEach
    public void initEach() throws IOException {
        Date fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        evento = new Evento("Boda", "Comida", 150, 10, "Benetuser", fecha, "Boda-Comida");
        this.googleCalendarService.clearEvents();
        Event event = this.googleCalendarService.add(evento);
        evento.setId(event.getId());
    }

    @Test
    void eventoIsAddedToGoogleCalendar() throws IOException {
        List<Event> eventos = this.googleCalendarService.getEvents();

        assertEquals(1, eventos.size());
        assertEquals("Boda-Comida", eventos.get(0).getSummary());
        assertEquals("Personas: 150\nLocalidad: Benetuser\nConfirmada: No", eventos.get(0).getDescription());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-25")), eventos.get(0).getStart());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-26")), eventos.get(0).getEnd());
    }

    @Test
    void eventoIsUpdatedInGoogleCalendar() throws IOException {
        evento.setTitulo("Comunión-Cena");
        evento.setPersonas(99);
        evento.setLocalidad("Olleria");
        evento.setConfirmado(true);
        evento.setFecha(new GregorianCalendar(2022, Calendar.JULY, 28).getTime());

        this.googleCalendarService.update(evento);

        List<Event> eventos = this.googleCalendarService.getEvents();

        assertEquals(1, eventos.size());
        assertEquals("Comunión-Cena", eventos.get(0).getSummary());
        assertEquals("Personas: 99\nLocalidad: Olleria\nConfirmada: Sí", eventos.get(0).getDescription());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-28")), eventos.get(0).getStart());
        assertEquals(new EventDateTime().setDate(new DateTime("2022-07-29")), eventos.get(0).getEnd());
    }

    @Test
    void eventoIsDeletedInGoogleCalendar() throws IOException {
        this.googleCalendarService.delete(evento);

        List<Event> eventos = this.googleCalendarService.getEvents();

        assertEquals(0, eventos.size());
    }

    @AfterEach
    public void clearCalendar() throws IOException {
        this.googleCalendarService.clearEvents();
    }
}
