package com.example.eventos.google;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final String calendarId;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private Calendar service;

    Logger logger = LoggerFactory.getLogger(GoogleCalendarService.class);

    @Autowired
    public GoogleCalendarService(@Value("${google.calenarId}") String calendarId) {
        this.calendarId = calendarId;

        try {
            GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH)).createScoped(Collections.singleton(CalendarScopes.CALENDAR));

            // Build a new authorized API client service.
            final NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
            this.service = new Calendar.Builder(netHttpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
        catch (IOException | GeneralSecurityException e){
            logger.error(e.getMessage());
        }

    }

    public String add(Evento evento) {
        try{
            Event event = create(evento);

            return this.service.events().insert(this.calendarId, event).execute().getId();
        }catch (IOException e){
            logger.error(e.getMessage());
        }

        return "";
    }

    public void update(Evento evento){
        try{
            Event event = create(evento);

            service.events().update(this.calendarId, evento.getId(), event).execute();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    public void delete(Evento evento) {
        try{
            this.service.events().delete(this.calendarId, evento.getId()).execute();
        }catch (IOException e){
            logger.error(e.getMessage());
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

    public Event getEventById(String id) throws IOException {
        return service.events().get(this.calendarId, id).execute();
    }

    public void clearEventById(String id) {
        try {
            this.service.events().delete(this.calendarId, id).execute();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


}