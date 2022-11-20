package com.example.eventos.protagonista;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.persona.Persona;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEvento.TipoEvento;
import com.example.eventos.tipoProtagonista.TipoProtagonista;
import com.example.eventos.tipoProtagonista.TipoProtagonistaService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProtagonistaController.class)
@Import(SecurityConfiguration.class)
class ProtagonistaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private TipoProtagonistaService tipoProtagonistaService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getProtagonistasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/protagonistasVer.html");

        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById("eventoId")).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/protagonistas")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEliminarProtagonistaTest() throws Exception {
        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista1);
        protagonistas.add(protagonista2);

        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById("eventoId")).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/protagonistas/eliminar")
                .param("eventoId", evento.getId())
                .param("protagonistaIndex", "1");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/evento/protagonistas?eventoId=eventoId"));

        verify(eventoService, times(1)).update(evento);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getAnyadirProtagonistaTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/protagonistaAnyadir.html");

        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        List<TipoProtagonista> tipoProtagonistas = new ArrayList<>();
        TipoProtagonista tipoProtagonista1 = new TipoProtagonista("novio/novia");
        TipoProtagonista tipoProtagonista2 = new TipoProtagonista("padre/madre");
        tipoProtagonistas.add(tipoProtagonista1);
        tipoProtagonistas.add(tipoProtagonista2);

        when(eventoService.getById("eventoId")).thenReturn(evento);
        when(tipoProtagonistaService.getTipoProtagonistas()).thenReturn(tipoProtagonistas);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/protagonistas/anyadir")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void postSaveProtagonistaTest() throws Exception {
        List<Protagonista> protagonistas = new ArrayList<>();
        Protagonista protagonista1 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Pepe", "666777888", "pepe@correo.es"));
        Protagonista protagonista2 = new Protagonista(new TipoProtagonista("novioNovia"), new Persona("Antonio", "666777999", "antonio@correo.es"));
        protagonistas.add(protagonista2);

        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById("eventoId")).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/evento/protagonistas/anyadir")
                .with(csrf())
                .flashAttr("protagonista", protagonista1)
                .param("eventoId", evento.getId())
                .param("protagonistaIndex", "1");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/evento/protagonistas?eventoId=eventoId"));

        verify(eventoService, times(1)).update(evento);
    }
}
