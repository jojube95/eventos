package com.example.eventos.mesa;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoController;
import com.example.eventos.evento.EventoService;
import com.example.utilities.TestUtilities;
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

@WebMvcTest(MesaController.class)
public class MesaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private MesaService mesaService;

    @Test
    public void getMesasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/mesas.html");

        Evento evento = new Evento("idEvento", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");
        Mesa mesa1 = new Mesa("idEvento", "Pepe", 10, 1, true);
        Mesa mesa2 = new Mesa("idEvento", "Antonio", 6, 2, false);
        Mesa mesa3 = new Mesa("idEvento", "José", 7, 3, true);
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa1);
        mesas.add(mesa2);
        mesas.add(mesa3);

        when(eventoService.getById(evento.getId())).thenReturn(evento);
        when(mesaService.findByEvento(evento.getId())).thenReturn(mesas);

        String resultContent = this.mockMvc.perform(get("/evento/mesas").param("eventoId", evento.getId())).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }
}