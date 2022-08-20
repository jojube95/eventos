package com.example.eventos.evento;

import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.MesaRepository;
import com.example.eventos.google.GoogleCalendarService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final MesaRepository mesaRepository;

    private final InvitadoRepository invitadoRepository;

    private final GoogleCalendarService googleCalendarService;

    public EventoService(EventoRepository eventoRepository, MesaRepository mesaRepository, InvitadoRepository invitadoRepository, GoogleCalendarService googleCalendarService) {
        this.eventoRepository = eventoRepository;
        this.mesaRepository = mesaRepository;
        this.invitadoRepository = invitadoRepository;
        this.googleCalendarService = googleCalendarService;
    }

    public List<Evento> getEventos(){
        return eventoRepository.findAll();
    }

    public Evento getById(String id) {
        return eventoRepository.findEventoById(id);
    }

    public void save(Evento evento) {
        eventoRepository.save(evento);
        googleCalendarService.add(evento);
    }

    public void update(Evento evento) {
        eventoRepository.save(evento);
        googleCalendarService.update(evento);
    }

    public void delete(Evento evento) {
        eventoRepository.delete(evento);
        mesaRepository.deleteByIdEvento(evento.getId());
        invitadoRepository.deleteByIdEvento(evento.getId());
        googleCalendarService.delete(evento);
    }
}
