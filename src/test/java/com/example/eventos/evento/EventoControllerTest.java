package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.horarioEvento.HorarioEventoService;
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
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;

@WebMvcTest(EventoController.class)
@Import(SecurityConfiguration.class)
class EventoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private ParametrosService parametrosService;

    @MockBean
    private TipoEventoService tipoEventoService;

    @MockBean
    private HorarioEventoService horarioEventoService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getverEventosTestUsuario() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventosVerUsuario.html");

        Evento evento1 = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Evento evento2 = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);

        when(eventoService.getEventos()).thenReturn(eventos);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventosVer")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(eventoService, times(1)).getEventos();
        verify(parametrosService, times(1)).get();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getverEventosTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventosVerAdmin.html");

        Evento evento1 = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Evento evento2 = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);

        when(eventoService.getEventos()).thenReturn(eventos);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventosVer")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(eventoService, times(1)).getEventos();
        verify(parametrosService, times(1)).get();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEventoAnyadirTestUsuario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoAnyadir");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(tipoEventoService, times(0)).getTipoEventos();
        verify(horarioEventoService, times(0)).getHorarioEventos();
        verify(parametrosService, times(0)).get();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventoAnyadirTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoAnyadir.html");

        List<TipoEvento> tipoEventos = new ArrayList<>();
        TipoEvento tipoEvento1 = new TipoEvento("boda");
        TipoEvento tipoEvento2 = new TipoEvento("comunion");
        tipoEventos.add(tipoEvento1);
        tipoEventos.add(tipoEvento2);

        List<HorarioEvento> horarioEventos = new ArrayList<>();
        HorarioEvento horarioEvento1 = new HorarioEvento("comida");
        HorarioEvento horarioEvento2 = new HorarioEvento("cena");
        horarioEventos.add(horarioEvento1);
        horarioEventos.add(horarioEvento2);

        when(tipoEventoService.getTipoEventos()).thenReturn(tipoEventos);
        when(horarioEventoService.getHorarioEventos()).thenReturn(horarioEventos);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoAnyadir")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(tipoEventoService, times(1)).getTipoEventos();
        verify(horarioEventoService, times(1)).getHorarioEventos();
        verify(parametrosService, times(2)).get();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEventoUpdateTest() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoUpdate")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(tipoEventoService, times(0)).getTipoEventos();
        verify(horarioEventoService, times(0)).getHorarioEventos();
        verify(parametrosService, times(0)).get();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventoUpdateTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoUpdate.html");

        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<TipoEvento> tipoEventos = new ArrayList<>();
        TipoEvento tipoEvento1 = new TipoEvento("boda");
        TipoEvento tipoEvento2 = new TipoEvento("comunion");
        tipoEventos.add(tipoEvento1);
        tipoEventos.add(tipoEvento2);

        List<HorarioEvento> horarioEventos = new ArrayList<>();
        HorarioEvento horarioEvento1 = new HorarioEvento("comida");
        HorarioEvento horarioEvento2 = new HorarioEvento("cena");
        horarioEventos.add(horarioEvento1);
        horarioEventos.add(horarioEvento2);

        when(eventoService.getById(evento.getId())).thenReturn(evento);
        when(tipoEventoService.getTipoEventos()).thenReturn(tipoEventos);
        when(horarioEventoService.getHorarioEventos()).thenReturn(horarioEventos);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoUpdate")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(eventoService, times(1)).getById(evento.getId());
        verify(tipoEventoService, times(1)).getTipoEventos();
        verify(horarioEventoService, times(1)).getHorarioEventos();
        verify(parametrosService, times(2)).get();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void postEventoUpdateTestUsuario() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoUpdate")
                .with(csrf())
                .flashAttr("evento", evento);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).update(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void postEventoUpdateTestAdmin() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        Evento eventoUpdated = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida2"), new Personas(51, 16), "Olleria2", fecha, 81, 16, true, new ArrayList<>(), "Comunión-Comida2", "Sala2", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoUpdate")
                .with(csrf())
                .flashAttr("evento", eventoUpdated);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).update(eventoUpdated);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEventoVerTestUsuario() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoVerUsuario.html");

        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoVer")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventoVerTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoVerAdmin.html");

        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoVer")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void postEventoAnyadirTestUsuario() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoAnyadir")
                .with(csrf())
                .flashAttr("evento", evento);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).save(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void postEventoAnyadirTestAdmin() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoAnyadir")
                .with(csrf())
                .flashAttr("evento", evento);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).save(evento);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEliminarEventoTestUsuario() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eliminarEvento")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).delete(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEliminarEventoTestAdmin() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eliminarEvento")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).delete(evento);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getUpdateFechaTest() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/updateFecha")
                .param("eventoId", evento.getId())
                .param("fecha", new GregorianCalendar(2022, Calendar.JULY, 10).getTime().toString());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).update(evento);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getCalcularPersonasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoPersonasConfirmModal.html");

        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.calcularPersonas("id")).thenReturn(new Personas(2, 0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/calcularPersonas")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));

        verify(eventoService, times(1)).calcularPersonas("id");
    }
}
