package com.example.eventos.evento;

import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.MesaRepository;
import com.example.google.calendar.GoogleCalendarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

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
        evento = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", new Date(), "Boda-Cena");
    }

    @Test
    public void findEventosByTipoTest(){
        eventoService.findEventosByTipo(evento.getTipo());
        verify(eventoRepository, times(1)).findEventoByTipo(evento.getTipo());
    }

    @Test
    public void getEventosTest(){
        eventoService.getEventos();
        verify(eventoRepository, times(1)).findAll();
    }

    @Test
    public void getEventoByIdTest(){
        eventoService.getById(evento.getId());
        verify(eventoRepository, times(1)).findEventoById(evento.getId());
    }

    @Test
    public void saveEventoTest(){
        eventoService.save(evento);
        verify(eventoRepository, times(1)).save(evento);
        verify(googleCalendarService, times(1)).add(evento);
    }

    @Test
    public void updateEventoTest(){
        eventoService.update(evento);
        verify(eventoRepository, times(1)).save(evento);
        verify(googleCalendarService, times(1)).update(evento);
    }

    @Test
    public void deleteEventoTest(){
        eventoService.delete(evento);
        verify(eventoRepository, times(1)).delete(evento);
        verify(mesaRepository, times(1)).deleteByIdEvento(evento.getId());
        verify(invitadoRepository, times(1)).deleteByIdEvento(evento.getId());
        verify(googleCalendarService, times(1)).delete(evento);
    }

}
