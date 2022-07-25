package com.example.eventos.evento;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;

@WebMvcTest(EventoController.class)
public class EventoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private MesaService mesaService;

    @MockBean
    private InvitadoService invitadoService;

    @Test
    public void getVerEventosTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/verEventos.html");

        Evento evento1 = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");
        Evento evento2 = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);

        when(eventoService.getEventos()).thenReturn(eventos);

        String resultContent = this.mockMvc.perform(get("/verEventos")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    public void getAnyadirEventoTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/anyadirEvento.html");

        String resultContent = this.mockMvc.perform(get("/anyadirEvento")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    public void getUpdateEventoTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/updateEvento.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        String resultContent = this.mockMvc.perform(get("/updateEvento").param("eventoId", evento.getId())).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    public void postUpdateEventoTest() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        this.mockMvc.perform(post("/updateEvento").flashAttr("evento", evento)).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/verEventos"));

        verify(eventoService, times(1)).update(evento);
    }

    @Test
    public void getVerEventoTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/verEvento.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        String resultContent = this.mockMvc.perform(get("/verEvento").param("eventoId", evento.getId())).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    public void postAnyadirEventoTest() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        this.mockMvc.perform(post("/anyadirEvento").flashAttr("evento", evento)).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).save(evento);
    }

    @Test
    public void getEliminarEventoTest() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        this.mockMvc.perform(get("/eliminarEvento").param("eventoId", evento.getId())).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/verEventos"));

        verify(eventoService, times(1)).delete(evento);
    }

    @Test
    public void getUpdateFechaTest() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        this.mockMvc.perform(get("/evento/updateFecha").param("id", evento.getId()).param("fecha", new Date(2022, 3, 10).toString())).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).update(evento);
    }

    @Test
    public void getCalcularPersonasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoPersonasConfirmModal.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, new ArrayList<>(), "Comunión-Comida");
        List<Mesa> mesas = new ArrayList<Mesa>();
        Mesa mesa1 = new Mesa("id", "Antonio", 10, 1, true);
        Mesa mesa2 = new Mesa("id", "Antonio", 10, 1, true);
        mesas.add(mesa1);
        mesas.add(mesa2);
        List<Invitado> invitados = new ArrayList<Invitado>();
        Invitado invitado1 = new Invitado("id", "idMesa", "Pepe", "Descripcion");
        Invitado invitado2 = new Invitado("id", "idMesa", "Pepe", "Descripcion");
        invitados.add(invitado1);
        invitados.add(invitado2);
        when(mesaService.findByEvento(evento.getId())).thenReturn(mesas);
        when(invitadoService.findByMesa(null)).thenReturn(invitados);

        String resultContent = this.mockMvc.perform(get("/evento/calcularPersonas").param("eventoId", evento.getId())).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }
}
