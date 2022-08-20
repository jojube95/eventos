package com.example.eventos.evento;

import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.MesaRepository;
import com.example.eventos.google.GoogleCalendarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    EventoRepository eventoRepository;

    @Mock
    MesaRepository mesaRepository;

    @Mock
    InvitadoRepository invitadoRepository;

    @Mock
    GoogleCalendarService googleCalendarService;

    @InjectMocks
    EventoService eventoService;

    Evento evento;

    @BeforeEach
    public void initEach(){
        Date fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        evento = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");
    }

    @Test
    void getEventosTest(){
        eventoService.getEventos();
        verify(eventoRepository, times(1)).findAll();
    }

    @Test
    void getEventoByIdTest() throws Exception {
        eventoService.getById(evento.getId());
        verify(eventoRepository, times(1)).findEventoById(evento.getId());
    }

    @Test
    void saveEventoTest() throws IOException {
        eventoService.save(evento);
        verify(eventoRepository, times(1)).save(evento);
        verify(googleCalendarService, times(1)).add(evento);
    }

    @Test
    void updateEventoTest() throws IOException {
        eventoService.update(evento);
        verify(eventoRepository, times(1)).save(evento);
        verify(googleCalendarService, times(1)).update(evento);
    }

    @Test
    void deleteEventoTest() throws IOException {
        eventoService.delete(evento);
        verify(eventoRepository, times(1)).delete(evento);
        verify(mesaRepository, times(1)).deleteByIdEvento(evento.getId());
        verify(invitadoRepository, times(1)).deleteByIdEvento(evento.getId());
        verify(googleCalendarService, times(1)).delete(evento);
    }

}
