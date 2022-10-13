package com.example.eventos.evento;

import com.example.eventos.eventoEmpleado.EventoEmpleado;
import com.example.eventos.eventoEmpleado.EventoEmpleadoRepository;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaRepository;
import com.example.eventos.google.GoogleCalendarService;
import com.example.eventos.personas.Personas;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.eventos.config.Constants.INVITADO_TIPO_NINYO;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final MesaRepository mesaRepository;

    private final InvitadoRepository invitadoRepository;

    private final EventoEmpleadoRepository eventoEmpleadoRepository;

    private final GoogleCalendarService googleCalendarService;

    public EventoService(EventoRepository eventoRepository, MesaRepository mesaRepository, InvitadoRepository invitadoRepository,
                         GoogleCalendarService googleCalendarService, EventoEmpleadoRepository eventoEmpleadoRepository) {
        this.eventoRepository = eventoRepository;
        this.mesaRepository = mesaRepository;
        this.invitadoRepository = invitadoRepository;
        this.googleCalendarService = googleCalendarService;
        this.eventoEmpleadoRepository = eventoEmpleadoRepository;
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

    public List<Evento> getByEmpleadoId(String empleadoId) {
        List<EventoEmpleado> eventosEmpleado = eventoEmpleadoRepository.findByEmpleadoId(empleadoId);

        List<Evento> eventos = new ArrayList<>();
        for (EventoEmpleado eventoEmpleado: eventosEmpleado) {
            eventos.add(eventoRepository.findEventoById(eventoEmpleado.getEventoId()));
        }

        return eventos;
    }

    public Personas calcularPersonas(String eventoId) {
        List<Mesa> mesas = mesaRepository.findByEventoIdOrderByNumeroAsc(eventoId);
        int mayores = 0;
        int ninyos = 0;
        for (Mesa mesa : mesas) {
            List<Invitado> invitados = invitadoRepository.findByMesaId(mesa.getId());
            for (Invitado invitado: invitados) {
                if(INVITADO_TIPO_NINYO.equals(invitado.getTipo())){
                    ninyos++;
                }
                else{
                    mayores++;
                }
            }
        }

        return new Personas(mayores, ninyos);
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
        mesaRepository.deleteByEventoId(evento.getId());
        invitadoRepository.deleteByEventoId(evento.getId());
        googleCalendarService.delete(evento);
    }
}
