package com.example.eventos.invitado;

import com.example.eventos.evento.Evento;
import com.example.eventos.mesa.Mesa;
import com.example.utilities.TestUtilities;
import io.cucumber.java.sl.In;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvitadoController.class)
public class InvitadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvitadoService invitadoService;

    @Test
    public void getInvitadosTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/invitadosModal.html");

        Evento evento = new Evento("idEvento", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");
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
