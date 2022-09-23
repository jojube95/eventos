package com.example.eventos.evento;

import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.mesa.MesaRepository;
import com.example.eventos.google.GoogleCalendarService;
import com.example.eventos.security.AuthenticationConfiguration;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.usuario.Usuario;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

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
        evento = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
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
        verify(mesaRepository, times(1)).deleteByIdEvento(evento.getId());
        verify(invitadoRepository, times(1)).deleteByIdEvento(evento.getId());
        verify(googleCalendarService, times(1)).delete(evento);
    }

}
