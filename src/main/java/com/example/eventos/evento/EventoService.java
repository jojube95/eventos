package com.example.eventos.evento;

import com.example.eventos.eventoEmpleado.EventoEmpleado;
import com.example.eventos.eventoEmpleado.EventoEmpleadoRepository;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaRepository;
import com.example.eventos.google.GoogleCalendarService;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEvento.TipoEvento;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.eventos.config.Constants.EVENTO_TIPO_INDIVIDUAL;

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

    public List<Evento> getEventosOrderByDate(){
        return eventoRepository.findAllByOrderByFechaAsc();
    }

    public Evento getById(String id) {
        return eventoRepository.findEventoById(id);
    }

    public List<Evento> getByEmpleadoId(String empleadoId) {
        List<EventoEmpleado> eventosEmpleado = eventoEmpleadoRepository.findByEmpleadoId(empleadoId);

        List<Evento> eventos = new ArrayList<>();
        for (EventoEmpleado eventoEmpleado: eventosEmpleado) {
            eventos.add(eventoRepository.findEventoById(eventoEmpleado.getEvento().getId()));
        }

        return eventos;
    }

    public Personas calcularPersonas(Evento evento) {
        List<Mesa> mesas = mesaRepository.findByEventoIdOrderByNumeroAsc(evento.getId());
        Personas personas = new Personas(0, 0);
        for (Mesa mesa : mesas) {
            // TODO: Check if could be done using polymorphirsm
            if(evento.getTipo().equals(new TipoEvento(EVENTO_TIPO_INDIVIDUAL))) {
                personas = mesa.incrementPersonas(personas);
            }
            else{
                List<Invitado> invitados = invitadoRepository.findByMesaId(mesa.getId());
                for (Invitado invitado: invitados) {
                    personas = invitado.incrementPersonas(personas);
                }
            }
        }
        return personas;
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

    public List<Evento> getEventosByYear(int year){
        Date from = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
        Date to = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();

        return eventoRepository.findAllByFechaBetween(from, to);
    }
}
