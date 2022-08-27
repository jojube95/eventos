package com.example.eventos.evento;

import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.MesaRepository;
import com.example.eventos.google.GoogleCalendarService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Date;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return eventoRepository.findAll();
        }
        else{
            Date date = DateUtils.addMonths(new Date(), 2);
            return eventoRepository.findByFechaBefore(date);
        }

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
