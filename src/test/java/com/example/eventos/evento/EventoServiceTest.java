package com.example.eventos.evento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

    @Mock
    EventoRepository eventoRepository;

    @InjectMocks
    EventoService eventoService;

    Evento evento1;
    Evento evento2;
    List<Evento> eventosByTipo;
    List<Evento> eventos;

    @BeforeEach
    public void initEach(){
        evento1 = new Evento("Boda", "Cena", 150, new Date());
        evento2 = new Evento("Comunión", "Cena", 100, new Date());
        eventosByTipo = new ArrayList<>();
        eventos = new ArrayList<>();
        eventosByTipo.add(evento1);
        eventos.add(evento1);
        eventos.add(evento2);
    }

    @Test
    public void findEventosByTipoTest(){
        when(eventoRepository.findEventoByTipo("Boda")).thenReturn(eventosByTipo);

        Evento eventoExpected = new Evento("Boda", "Cena", 150, new Date());
        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(eventoExpected);

        assertEquals(eventosExpected, eventoService.findEventosByTipo("Boda"));
    }

    @Test
    public void getEventosTest(){
        when(eventoRepository.findAll()).thenReturn(eventos);

        Evento eventoExpected1 = new Evento("Boda", "Cena", 150, new Date());
        Evento eventoExpected2 = new Evento("Comunión", "Cena", 100, new Date());
        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(eventoExpected1);
        eventosExpected.add(eventoExpected2);

        assertEquals(eventosExpected, eventoService.getEventos());
    }

}
