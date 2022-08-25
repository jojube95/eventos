package com.example.eventos.calendario;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.security.SecurityConfiguration;
import com.example.utilities.TestUtilities;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.*;
import static com.example.utilities.TestUtilities.processContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalendarioController.class)
@Import(SecurityConfiguration.class)
class CalendarioControllerTest {

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
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getRedirectCalendario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getCalendario() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/calendario.html");

        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena");

        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);

        when(eventoService.getEventos()).thenReturn(eventos);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/calendario")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }
}
