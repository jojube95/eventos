package com.example.eventos.mesa;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MesaController.class)
@Import(SecurityConfiguration.class)
class MesaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private MesaService mesaService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getMesasTest() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/mesas.html");

        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        MesaReserva mesa1 = new MesaReserva("id", "eventoId", new Personas(10, 1), 1, "descripcion", "Pepe", true);
        MesaReserva mesa2 = new MesaReserva("id", "eventoId", new Personas(6, 1), 2, "descripcion", "Antonio", false);
        MesaReserva mesa3 = new MesaReserva("id", "eventoId", new Personas(7, 1), 3, "descripcion", "José", true);
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
        MesaReserva mesa1 = new MesaReserva("mesaId", "eventoId", new Personas(10, 1), 1, "descripcion", "Pepe", true);

        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa1);

        when(mesaService.findByEventoOrderByNumero("eventoId")).thenReturn(mesas);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/mesas/generarListado")
                .locale(new Locale("es", "ES"))
                .param("eventoId", "eventoId");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray();

        verify(mesaService, times(1)).listadoPdfGenerator(mesas);
    }
}
