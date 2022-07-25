package com.example.google.calendar;

import com.example.eventos.evento.Evento;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Service
public class GoogleCalendarService {
    private static final String APPLICATION_NAME = "eventos";
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials/googleAccountService.json";
    private static final String CALENDAR_ID = "fkoiv6b5mmfr6lga0mm3q5hips@group.calendar.google.com";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private Calendar service;

    public GoogleCalendarService() {
        try {
            GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH)).createScoped(Collections.singleton(CalendarScopes.CALENDAR));

            // Build a new authorized API client service.
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            this.service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
        catch (IOException | GeneralSecurityException e){
            System.err.println(e.getMessage());
        }

    }

    public void add(Evento evento) {
        try{
            Event event = create(evento);

            this.service.events().insert(CALENDAR_ID, event).execute();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void update(Evento evento){
        try{
            Event event = create(evento);

            service.events().update(CALENDAR_ID, evento.getId(), event).execute();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void delete(Evento evento) {
        try{
            this.service.events().delete(CALENDAR_ID, evento.getId()).execute();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    static Event create(Evento evento){
        Event event = new Event()
                .setId(evento.getId())
                .setSummary(evento.getTitulo())
                .setDescription(evento.toString());

        Date startDate = evento.getFecha();
        Date endDate = new Date(startDate.getTime() + 86400000); // An all-day event is 1 day (or 86400000 ms) long

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);

        // Must use the setDate() method for an all-day event (setDateTime() is used for timed events)
        EventDateTime startEventDateTime = new EventDateTime().setDate(new DateTime(startDateStr));
        EventDateTime endEventDateTime = new EventDateTime().setDate(new DateTime(endDateStr));

        event.setStart(startEventDateTime);
        event.setEnd(endEventDateTime);

        return event;
    }


}
