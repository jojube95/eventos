package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.empleado.Empleado;
import com.example.eventos.eventoEmpleado.EventoEmpleado;
import com.example.eventos.eventoEmpleado.EventoEmpleadoRepository;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoFactory;
import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaRepository;
import com.example.eventos.google.GoogleCalendarService;
import com.example.eventos.persona.Persona;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import com.example.eventos.tipoEvento.TipoEvento;
import com.example.eventos.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    EventoEmpleadoRepository eventoEmpleadoRepository;

    @Mock
    GoogleCalendarService googleCalendarService;

    @InjectMocks
    EventoService eventoService;

    Evento evento;

    @BeforeEach
    public void initEach(){
        Date fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        evento = new Evento(new TipoEvento("boda"), new HorarioEvento("cena"), new Personas(150, 10), "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
    }

    @Test
    void getEventosUsuarioTest(){
        Usuario usuario = new Usuario("usuario", "USUARIO", "ROLE_USUARIO");

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        doReturn(usuario.getAuthorities()).when(authentication).getAuthorities();

        eventoService.getEventos();
        verify(eventoRepository, times(1)).findByFechaBefore(any());

        Mockito.reset(authentication, securityContext);
    }

    @Test
    void getEventosAdminTest(){
        Usuario usuario = new Usuario("admin", "ADMIN", "ROLE_ADMIN");


        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        doReturn(usuario.getAuthorities()).when(authentication).getAuthorities();

        eventoService.getEventos();
        verify(eventoRepository, times(1)).findAll();

        Mockito.reset(authentication, securityContext);
    }

    @Test
    void getEventoByIdTest() {
        eventoService.getById(evento.getId());
        verify(eventoRepository, times(1)).findEventoById(evento.getId());
    }

    @Test
    void getByEmpleadoIdTest() {
        Date fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo"), true, true);

        EventoEmpleado eventoEmpleado = new EventoEmpleado("id", evento, empleado, true, 0);
        List<EventoEmpleado> eventoEmpleados = new ArrayList<>();
        eventoEmpleados.add(eventoEmpleado);

        when(eventoEmpleadoRepository.findByEmpleadoId("idEmpleado1")).thenReturn(eventoEmpleados);
        when(eventoRepository.findEventoById("idEvento1")).thenReturn(evento);

        List<Evento> eventosExpected = new ArrayList<>();
        eventosExpected.add(evento);

        assertEquals(eventosExpected, eventoService.getByEmpleadoId("idEmpleado1"));
    }

    @Test
    void calcularPersonasTest() {
        List<Mesa> mesas = new ArrayList<>();
        Mesa mesa1 = new Mesa("mesaId1", "eventoId", new Personas(2, 1), 1, "descripcion1");
        Mesa mesa2 = new Mesa("mesaId2", "eventoId", new Personas(3, 1), 2, "descripcion2");
        mesas.add(mesa1);
        mesas.add(mesa2);

        List<Invitado> invitados1 = new ArrayList<>();
        List<Invitado> invitados2 = new ArrayList<>();

        Invitado invitado1 = InvitadoFactory.crearInvitado("id1", "eventoId", "mesaId1", "nombre", "Mayor", "descripcion");
        Invitado invitado2 = InvitadoFactory.crearInvitado("id2", "eventoId", "mesaId1", "nombre", "Mayor", "descripcion");
        Invitado invitado3 = InvitadoFactory.crearInvitado("id3", "eventoId", "mesaId1", "nombre", "Niño", "descripcion");

        Invitado invitado4 = InvitadoFactory.crearInvitado("id4", "eventoId", "mesaId2", "nombre", "Mayor", "descripcion");
        Invitado invitado5 = InvitadoFactory.crearInvitado("id5", "eventoId", "mesaId2", "nombre", "Niño", "descripcion");

        invitados1.add(invitado1);
        invitados1.add(invitado2);
        invitados1.add(invitado3);

        invitados2.add(invitado4);
        invitados2.add(invitado5);

        when(mesaRepository.findByEventoIdOrderByNumeroAsc("eventoId")).thenReturn(mesas);
        when(invitadoRepository.findByMesaId("mesaId1")).thenReturn(invitados1);
        when(invitadoRepository.findByMesaId("mesaId2")).thenReturn(invitados2);

        Personas personasExpected = new Personas(3, 2);
        assertEquals(personasExpected, eventoService.calcularPersonas("eventoId"));
    }

    @Test
    void saveEventoTest() {
        eventoService.save(evento);
        verify(eventoRepository, times(1)).save(evento);
        verify(googleCalendarService, times(1)).add(evento);
    }

    @Test
    void eventoUpdateTest() {
        eventoService.update(evento);
        verify(eventoRepository, times(1)).save(evento);
        verify(googleCalendarService, times(1)).update(evento);
    }

    @Test
    void deleteEventoTest() {
        eventoService.delete(evento);
        verify(eventoRepository, times(1)).delete(evento);
        verify(mesaRepository, times(1)).deleteByEventoId(evento.getId());
        verify(invitadoRepository, times(1)).deleteByEventoId(evento.getId());
        verify(googleCalendarService, times(1)).delete(evento);
    }

}
