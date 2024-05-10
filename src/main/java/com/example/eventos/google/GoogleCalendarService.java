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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static com.example.eventos.config.Constants.EVENTO_FECHA_FORMAT;

@Service
public class GoogleCalendarService {
    private static final String APPLICATION_NAME = "eventos";

    @Value("${google.calenarId}")
    private String calendarId;

    @Value("${google.privateKeyId}")
    private String privateKeyId;

    @Value("${google.privateKey}")
    private String privateKey;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private Calendar service;

    final Logger logger = LoggerFactory.getLogger(GoogleCalendarService.class);

    @PostConstruct
    private void init() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(generateJSONCredential(this.privateKeyId, this.privateKey).getBytes());

            GoogleCredential credential = GoogleCredential.fromStream(byteArrayInputStream)
                    .createScoped(Collections.singleton(CalendarScopes.CALENDAR));

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

        DateFormat dateFormat = new SimpleDateFormat(EVENTO_FECHA_FORMAT);
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

    private String generateJSONCredential(String privateKeyId, String privateKey) {
        return "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"eventos-356615\",\n" +
                "  \"private_key_id\":\"" + privateKeyId + "\",\n" +
                "  \"private_key\":\"" + privateKey + "\",\n" +
                "  \"client_email\": \"eventos@eventos-356615.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"103358250117767286325\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/eventos%40eventos-356615.iam.gserviceaccount.com\"\n" +
                "}";
    }
}