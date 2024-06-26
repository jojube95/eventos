package com.example.eventos.distribucion;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEvento.TipoEvento;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DistribucionRestController.class)
@Import(SecurityConfiguration.class)
class DistribucionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void saveTest() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        String expectedResponse = "{\"id\":\"id\",\"tipo\":{\"value\":\"comunion\"},\"horario\":{\"value\":\"comida\"},\"personas\":{\"mayores\":50,\"ninyos\":15},\"localidad\":\"Olleria\",\"precioMenu\":80.0,\"precioMenuNinyos\":15.0,\"confirmado\":true,\"titulo\":\"ComuniÃ³n-Comida\",\"sala\":\"Sala1\",\"descripcion\":\"descripcion\",\"fecha\":\"2010-02-03T00:00:00.000+00:00\",\"protagonistas\":[],\"distribucion\":{\"mapa\":\"distribucionJson\"},\"eventoWithMesasConReserva\":false}";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/distribucion/guardar")
                .with(csrf())
                .param("eventoId", evento.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("distribucionJson");

        evento.setDistribucion(new Distribucion("distribucionJson"));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        verify(eventoService, times(1)).update(evento);
    }
}
