package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEvento.TipoEvento;
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
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventoRestController.class)
@Import(SecurityConfiguration.class)
class EventoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private EventoService eventoService;

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void updateTestUsuario() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        int personas = 60;

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/updatePersonas")
                .with(csrf())
                .param("eventoId", evento.getId())
                .param("personas", String.valueOf(personas));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).update(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void updateTestAdmin() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Personas personas = new Personas(60, 20);

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        String expectedResponse = "{\"id\":\"id\",\"tipo\":{\"value\":\"comunion\"},\"horario\":{\"value\":\"comida\"},\"personas\":{\"mayores\":60,\"ninyos\":20},\"localidad\":\"Olleria\",\"precioMenu\":80.0,\"precioMenuNinyos\":15.0,\"confirmado\":true,\"titulo\":\"ComuniÃ³n-Comida\",\"sala\":\"Sala1\",\"descripcion\":\"descripcion\",\"fecha\":\"2010-02-03T00:00:00.000+00:00\",\"protagonistas\":[],\"distribucion\":{\"mapa\":\"Distribucion\"},\"eventoWithMesasConReserva\":false}";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/updatePersonas")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(personas))
                .param("eventoId", evento.getId());


        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        verify(eventoService, times(1)).update(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventosByYearTest() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getEventosByYear(2010)).thenReturn(List.of(evento));

        String expectedResponse = "[{\"id\":\"id\",\"tipo\":{\"value\":\"comunion\"},\"horario\":{\"value\":\"comida\"},\"personas\":{\"mayores\":50,\"ninyos\":15},\"localidad\":\"Olleria\",\"precioMenu\":80.0,\"precioMenuNinyos\":15.0,\"confirmado\":true,\"titulo\":\"ComuniÃ³n-Comida\",\"sala\":\"Sala1\",\"descripcion\":\"descripcion\",\"fecha\":\"2010-02-03T00:00:00.000+00:00\",\"protagonistas\":[],\"distribucion\":{\"mapa\":\"Distribucion\"},\"eventoWithMesasConReserva\":false}]";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/eventos")
                .with(csrf())
                .param("year", String.valueOf(2010));


        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        verify(eventoService, times(1)).getEventosByYear(2010);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventoTest() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        String expectedResponse = "{\"id\":\"id\",\"tipo\":{\"value\":\"comunion\"},\"horario\":{\"value\":\"comida\"},\"personas\":{\"mayores\":50,\"ninyos\":15},\"localidad\":\"Olleria\",\"precioMenu\":80.0,\"precioMenuNinyos\":15.0,\"confirmado\":true,\"titulo\":\"ComuniÃ³n-Comida\",\"sala\":\"Sala1\",\"descripcion\":\"descripcion\",\"fecha\":\"2010-02-03T00:00:00.000+00:00\",\"protagonistas\":[],\"distribucion\":{\"mapa\":\"Distribucion\"},\"eventoWithMesasConReserva\":false}";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/evento")
                .with(csrf())
                .param("id", evento.getId());


        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        verify(eventoService, times(1)).getById(evento.getId());
    }
}
