package com.example.eventos.mesa;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoFactory;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEvento.TipoEvento;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MesaController.class)
@Import(SecurityConfiguration.class)
class MesaControllerTest {

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
    void getMesasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/mesas.html");

        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Mesa mesa1 = new Mesa("eventoId", "Pepe", new Personas(10, 1), 1, true, "descripcion");
        Mesa mesa2 = new Mesa("eventoId", "Antonio", new Personas(6, 1), 2, false, "descripcion");
        Mesa mesa3 = new Mesa("eventoId", "José", new Personas(7, 1), 3, true, "descripcion");
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa1);
        mesas.add(mesa2);
        mesas.add(mesa3);

        when(eventoService.getById(evento.getId())).thenReturn(evento);
        when(mesaService.findByEvento(evento.getId())).thenReturn(mesas);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/mesas")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void generarListadoTest() throws Exception {
        Mesa mesa1 = new Mesa("mesaId", "eventoId", "Pepe", new Personas(10, 1), 1, true, "descripcion");
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa1);

        Invitado invitado1 = InvitadoFactory.crearInvitado("id", "eventoId", "mesaId", "Pepe", "Mayor", "");
        Invitado invitado2 = InvitadoFactory.crearInvitado("id", "eventoId", "mesaId", "Antonio", "Mayor", "Vegano");
        Invitado invitado3 = InvitadoFactory.crearInvitado("id", "eventoId", "mesaId", "José", "Mayor", "");
        List<Invitado> invitados = new ArrayList<>();
        invitados.add(invitado1);
        invitados.add(invitado2);
        invitados.add(invitado3);

        when(mesaService.findByEventoOrderByNumero("eventoId")).thenReturn(mesas);
        when(invitadoService.findByMesa("mesaId")).thenReturn(invitados);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/mesas/generarListado")
                .locale(new Locale("es", "ES"))
                .param("eventoId", "eventoId");

        byte[] resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray();

        assertEquals(1226, resultContent.length);
    }
}
