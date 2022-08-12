package com.example.eventos.invitado;

import com.example.eventos.evento.Evento;
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

@WebMvcTest(InvitadoController.class)
public class InvitadoControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvitadoService invitadoService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    public void getInvitadosTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/invitadosModal.html");

        Evento evento = new Evento("idEvento", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida");
        Invitado invitado1 = new Invitado(evento.getId(), "idMesa", "Pepe", "");
        Invitado invitado2 = new Invitado(evento.getId(), "idMesa", "Antonio", "Vegano");
        Invitado invitado3 = new Invitado(evento.getId(), "idMesa", "José", "");
        List<Invitado> invitados = new ArrayList<>();
        invitados.add(invitado1);
        invitados.add(invitado2);
        invitados.add(invitado3);

        when(invitadoService.findByMesa("idMesa")).thenReturn(invitados);

        String resultContent = this.mockMvc.perform(get("/evento/mesas/invitados").param("idEvento", evento.getId()).param("idMesa", "idMesa")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }
}
