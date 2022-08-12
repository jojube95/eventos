package com.example.eventos.calendario;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.utilities.TestUtilities;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalendarioController.class)
public class CalendarioControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    public void getCalendario() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/calendario.html");

        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");

        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);

        when(eventoService.getEventos()).thenReturn(eventos);

        String resultContent = this.mockMvc.perform(get("/calendario")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }
}
