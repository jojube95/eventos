package com.example.eventos.mesa;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.security.SecurityConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MesaRestController.class)
@Import(SecurityConfiguration.class)
class MesaRestControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private MesaService mesaService;

    @MockBean
    private InvitadoService invitadoService;

    @MockBean
    private EventoService eventoService;

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void addTestUsuario() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 3, 1, 2, true, "descripcion");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/add")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(mesaService, times(0)).save(mesa);
        verify(invitadoService, times(0)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado1", "Mayor", ""));
        verify(invitadoService, times(0)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado2", "Mayor", ""));
        verify(invitadoService, times(0)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado3", "Mayor", ""));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void addTestAdmin() throws Exception {
        Evento evento = new Evento("idEvento", "Comunion", "Comida", 50, 15, "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        Mesa mesa = new Mesa("idEvento", "Pepe", 3, 1, 2, true, "descripcion");

        when(eventoService.getById(mesa.getIdEvento())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/add")
                .with(csrf())
                .param("idEvento", mesa.getIdEvento())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).save(mesa);
        verify(invitadoService, times(1)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado1", "Mayor", ""));
        verify(invitadoService, times(1)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado2", "Mayor", ""));
        verify(invitadoService, times(1)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado3", "Mayor", ""));
        verify(invitadoService, times(1)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Niño1", "Ninyo", ""));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void deleteTestUsuario() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 10, 1, 2, true, "descripcion");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/delete")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(mesaService, times(0)).delete(mesa);
        verify(invitadoService, times(0)).deleteInvitados(mesa.getId());
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void deleteTestAdmin() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 10, 1, 2, true, "descripcion");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/delete")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).delete(mesa);
        verify(invitadoService, times(1)).deleteInvitados(mesa.getId());
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void updateTest() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 10, 1, 2, true, "descripcion");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/update")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(mesaService, times(0)).save(mesa);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void updateTestAdmin() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 10, 1, 2, true, "descripcion");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/update")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).save(mesa);
    }
}
