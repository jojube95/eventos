package com.example.eventos.grafico;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.parametros.Parametros;
import com.example.eventos.parametros.ParametrosService;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEvento.TipoEvento;
import com.example.eventos.tipoEvento.TipoEventoService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GraficoController.class)
@Import(SecurityConfiguration.class)
class GraficoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private ParametrosService parametrosService;

    @MockBean
    private TipoEventoService tipoEventoService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getGraficoBarrasTestUsuario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/graficoBarras");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).getEventosOrderByDate();
        verify(tipoEventoService, times(0)).getTipoEventos();
        verify(parametrosService, times(0)).get();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getGraficoBarrasTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/graficoBarras.html");

        List<Evento> eventos = new ArrayList<>();
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        eventos.add(evento);

        List<TipoEvento> tipoEventos = new ArrayList<>();
        TipoEvento tipoEvento1 = new TipoEvento("boda");
        TipoEvento tipoEvento2 = new TipoEvento("comunion");
        tipoEventos.add(tipoEvento1);
        tipoEventos.add(tipoEvento2);

        when(eventoService.getEventosOrderByDate()).thenReturn(eventos);
        when(tipoEventoService.getTipoEventos()).thenReturn(tipoEventos);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/graficoBarras")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(eventoService, times(1)).getEventosOrderByDate();
        verify(tipoEventoService, times(1)).getTipoEventos();
        verify(parametrosService, times(1)).get();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getGraficoPastelTestUsuario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/graficoPastel");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).getEventosOrderByDate();
        verify(tipoEventoService, times(0)).getTipoEventos();
        verify(parametrosService, times(0)).get();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getGraficoPastelTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/graficoPastel.html");

        List<Evento> eventos = new ArrayList<>();
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        eventos.add(evento);

        List<TipoEvento> tipoEventos = new ArrayList<>();
        TipoEvento tipoEvento1 = new TipoEvento("boda");
        TipoEvento tipoEvento2 = new TipoEvento("comunion");
        tipoEventos.add(tipoEvento1);
        tipoEventos.add(tipoEvento2);

        when(eventoService.getEventosOrderByDate()).thenReturn(eventos);
        when(tipoEventoService.getTipoEventos()).thenReturn(tipoEventos);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/graficoBarras")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(eventoService, times(1)).getEventosOrderByDate();
        verify(tipoEventoService, times(1)).getTipoEventos();
        verify(parametrosService, times(1)).get();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getGraficoDispersionTestUsuario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/graficoDispersion");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).getEventosOrderByDate();
        verify(tipoEventoService, times(0)).getTipoEventos();
        verify(parametrosService, times(0)).get();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getGraficoDispersionTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/graficoDispersion.html");

        List<Evento> eventos = new ArrayList<>();
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        eventos.add(evento);

        List<TipoEvento> tipoEventos = new ArrayList<>();
        TipoEvento tipoEvento1 = new TipoEvento("boda");
        TipoEvento tipoEvento2 = new TipoEvento("comunion");
        tipoEventos.add(tipoEvento1);
        tipoEventos.add(tipoEvento2);

        when(eventoService.getEventosOrderByDate()).thenReturn(eventos);
        when(tipoEventoService.getTipoEventos()).thenReturn(tipoEventos);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/graficoBarras")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(eventoService, times(1)).getEventosOrderByDate();
        verify(tipoEventoService, times(1)).getTipoEventos();
        verify(parametrosService, times(1)).get();
    }
}
