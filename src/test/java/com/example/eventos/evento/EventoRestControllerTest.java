package com.example.eventos.evento;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventoRestController.class)
public class EventoRestControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @Test
    public void updateTest() throws Exception {
        Evento evento = new Evento("id", "Comunion", "Comida", 50, 15, "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");
        int personas = 60;

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        String expectedResponse = "{\"id\":\"id\",\"tipo\":\"Comunion\",\"horario\":\"Comida\",\"personas\":60,\"ninyos\":15,\"localidad\":\"Olleria\",\"precioMenu\":80.0,\"precioMenuNinyos\":15.0,\"confirmado\":true,\"titulo\":\"ComuniÃ³n-Comida\",\"fecha\":\"2010-02-03T00:00:00.000+00:00\",\"protagonistas\":[],\"eventoIndividual\":false}";

        this.mockMvc.perform(post("/evento/updatePersonas").param("eventoId", evento.getId()).param("personas", String.valueOf(personas))).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        verify(eventoService, times(1)).update(evento);
    }
}
