package com.example.eventos.google;

import com.example.eventos.evento.Evento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.google.api.services.calendar.model.Event;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GoogleCalendarServiceTest {

    @Test
    void createTest(){
        Date fecha = new GregorianCalendar(2014, Calendar.JANUARY, 11).getTime();

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        Event createdEvent = GoogleCalendarService.create(evento);

        assertEquals("id", createdEvent.getId());
        assertEquals("Comunión-Comida", createdEvent.getSummary());
        assertEquals("Personas: 50\n" + "Localidad: Olleria\n" + "Confirmada: Sí", createdEvent.getDescription());
        assertEquals("2014-01-11", createdEvent.getStart().getDate().toString());
        assertEquals("2014-01-12", createdEvent.getEnd().getDate().toString());
    }
}
