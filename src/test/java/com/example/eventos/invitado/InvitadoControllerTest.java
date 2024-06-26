package com.example.eventos.invitado;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.horarioEvento.HorarioEvento;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvitadoController.class)
@Import(SecurityConfiguration.class)
class InvitadoControllerTest {

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
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getInvitadosTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/invitadosModal.html");

        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Invitado invitado1 = InvitadoFactory.crearInvitado("id", evento.getId(), "mesaId", "Pepe", "Mayor",  "");
        Invitado invitado2 = InvitadoFactory.crearInvitado("id1", evento.getId(), "mesaId", "Antonio", "Mayor", "Vegano");
        Invitado invitado3 = InvitadoFactory.crearInvitado("id3", evento.getId(), "mesaId", "José", "Mayor", "");
        List<Invitado> invitados = new ArrayList<>();
        invitados.add(invitado1);
        invitados.add(invitado2);
        invitados.add(invitado3);

        when(invitadoService.findByMesa("mesaId")).thenReturn(invitados);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/mesas/invitados")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId())
                .param("mesaId", "mesaId");

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }
}
