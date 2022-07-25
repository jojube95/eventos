package com.example.eventos.evento;

import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.MesaRepository;
import com.example.google.calendar.GoogleCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private InvitadoRepository invitadoRepository;

    @SuppressWarnings("FieldMayBeFinal")
    private GoogleCalendarService googleCalendarService = new GoogleCalendarService();

    public List<Evento> findEventosByTipo(String tipo){
        return eventoRepository.findEventoByTipo(tipo);
    }

    public List<Evento> getEventos(){
        return eventoRepository.findAll();
    }

    public Evento getById(String id){
        return eventoRepository.findEventoById(id);
    }

    public void save(Evento evento){
        eventoRepository.save(evento);
        googleCalendarService.add(evento);
    }

    public void update(Evento evento){
        eventoRepository.save(evento);
        googleCalendarService.update(evento);
    }

    public void delete(Evento evento){
        eventoRepository.delete(evento);
        mesaRepository.deleteByIdEvento(evento.getId());
        invitadoRepository.deleteByIdEvento(evento.getId());
        googleCalendarService.delete(evento);
    }
}
