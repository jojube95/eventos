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
public class GoogleCalendarServiceTest {
    @Test
    public void createTest(){
        Date fecha = new GregorianCalendar(2014, Calendar.JANUARY, 11).getTime();

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        Event createdEvent = GoogleCalendarService.create(evento);

        assertEquals(createdEvent.getId(), "id");
        assertEquals(createdEvent.getSummary(), "Comunión-Comida");
        assertEquals(createdEvent.getDescription(), "Personas: 50\n" + "Localidad: Olleria\n" + "Confirmada: Sí");
        assertEquals(createdEvent.getStart().getDate().toString(), "2014-01-11");
        assertEquals(createdEvent.getEnd().getDate().toString(), "2014-01-12");
    }
}
