package com.example.eventos.protagonista;

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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProtagonistaController.class)
public class ProtagonistaControllerTest {
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
    public void getProtagonistasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/verProtagonistas.html");

        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento evento = new Evento("eventoId", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        when(eventoService.getById("eventoId")).thenReturn(evento);

        String resultContent = this.mockMvc.perform(get("/evento/protagonistas").param("eventoId", evento.getId())).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    public void getEliminarProtagonistaTest() throws Exception {
        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento evento = new Evento("eventoId", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        when(eventoService.getById("eventoId")).thenReturn(evento);

        this.mockMvc.perform(get("/evento/protagonistas/eliminar").param("eventoId", evento.getId()).param("protagonistaIndex", "1")).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/evento/protagonistas?eventoId=eventoId"));

        verify(eventoService, times(1)).update(evento);
    }

    @Test
    public void getAnyadirProtagonistaTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/anyadirProtagonista.html");

        Evento evento = new Evento("eventoId", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida");

        when(eventoService.getById("eventoId")).thenReturn(evento);

        String resultContent = this.mockMvc.perform(get("/evento/protagonistas/anyadir").param("eventoId", evento.getId())).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = resultContent.replaceAll(" ", "");

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    public void postSaveProtagonistaTest() throws Exception {
        List<Protagonista> protagonistas = new ArrayList<Protagonista>();
        Protagonista protagonista1 = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        Protagonista protagonista2 = new Protagonista("Novio/a", "Antonio", "666777999", "antonio@correo.es");
        protagonistas.add(protagonista2);

        Evento evento = new Evento("eventoId", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida");

        when(eventoService.getById("eventoId")).thenReturn(evento);

        this.mockMvc.perform(post("/evento/protagonistas/anyadir").flashAttr("protagonista", protagonista1).param("eventoId", evento.getId()).param("protagonistaIndex", "1")).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/evento/protagonistas?eventoId=eventoId"));

        verify(eventoService, times(1)).update(evento);
    }
}
