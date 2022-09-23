package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.mesa.Mesa;
import com.example.eventos.mesa.MesaService;
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
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;

@WebMvcTest(EventoController.class)
@Import(SecurityConfiguration.class)
class EventoControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private MesaService mesaService;

    @MockBean
    private InvitadoService invitadoService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getverEventosTestUsuario() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/verEventosUsuario.html");

        Evento evento1 = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Evento evento2 = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);

        when(eventoService.getEventos()).thenReturn(eventos);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventosVer")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getverEventosTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/verEventosAdmin.html");

        Evento evento1 = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Evento evento2 = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);

        when(eventoService.getEventos()).thenReturn(eventos);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventosVer")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEventoAnyadirTestUsuario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoAnyadir");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventoAnyadirTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoAnyadir.html");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoAnyadir")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void geteventoUpdateTest() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoUpdate")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void geteventoUpdateTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoUpdate.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eventoUpdate")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void posteventoUpdateTestUsuario() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoUpdate")
                .with(csrf())
                .flashAttr("evento", evento);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).update(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void posteventoUpdateTestAdmin() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Evento eventoUpdated = new Evento("id", "Comunión2", "Comida2", 51, 16, "Olleria2", fecha, 81, 16, true, new ArrayList<>(), "Comunión-Comida2", "Sala2", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoUpdate")
                .with(csrf())
                .flashAttr("evento", eventoUpdated);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).update(eventoUpdated);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void geteventoVerTestUsuario() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoVerUsuario.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

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
    void geteventoVerTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoVerAdmin.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

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
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoAnyadir")
                .with(csrf())
                .flashAttr("evento", evento);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).save(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void postEventoAnyadirTestAdmin() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eventoAnyadir")
                .with(csrf())
                .flashAttr("evento", evento);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).save(evento);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEliminarEventoTestUsuario() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eliminarEvento")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoService, times(0)).delete(evento);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEliminarEventoTestAdmin() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/eliminarEvento")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).delete(evento);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getUpdateFechaTest() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        when(eventoService.getById(evento.getId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/updateFecha")
                .param("id", evento.getId())
                .param("fecha", new GregorianCalendar(2022, Calendar.JULY, 10).getTime().toString());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/calendario"));

        verify(eventoService, times(1)).update(evento);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getCalcularPersonasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoPersonasConfirmModal.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        List<Mesa> mesas = new ArrayList<>();
        Mesa mesa1 = new Mesa("id", "Antonio", 10, 1, 1, true, "descripcion");
        Mesa mesa2 = new Mesa("id", "Antonio", 10, 1, 1, true, "descripcion");
        mesas.add(mesa1);
        mesas.add(mesa2);
        List<Invitado> invitados = new ArrayList<>();
        Invitado invitado1 = new Invitado("id", "idMesa", "Pepe", "Mayor", "Descripcion");
        Invitado invitado2 = new Invitado("id", "idMesa", "Pepe", "Mayor", "Descripcion");
        invitados.add(invitado1);
        invitados.add(invitado2);
        when(mesaService.findByEvento(evento.getId())).thenReturn(mesas);
        when(invitadoService.findByMesa(null)).thenReturn(invitados);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/calcularPersonas")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }
}
