package com.example.eventos.eventoEmpleado;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventoEmpleadoController.class)
@Import(SecurityConfiguration.class)
class EventoEmpleadoControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @MockBean
    private EventoEmpleadoService eventoEmpleadoService;

    @MockBean
    private EventoService eventoService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEventoEmpleadosTestUsuario() throws Exception {
        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/empleados")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventoEmpleadosTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoEmpleados.html");

        Evento evento = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<Empleado> empleadosFijos = new ArrayList<>();
        List<Empleado> empleadosNoFijos = new ArrayList<>();
        Empleado empleadoFijo1 = new Empleado("id1", "tipo1", "nombre1", "telefono1", true);
        Empleado empleadoFijo2 = new Empleado("id2", "tipo2", "nombre2", "telefono2", true);
        Empleado empleadoNoFijo1 = new Empleado("id3", "tipo3", "nombre3", "telefono3", false);
        Empleado empleadoNoFijo2 = new Empleado("id4", "tipo4", "nombre4", "telefono4", false);
        empleadosFijos.add(empleadoFijo1);
        empleadosFijos.add(empleadoFijo2);
        empleadosNoFijos.add(empleadoNoFijo1);
        empleadosNoFijos.add(empleadoNoFijo2);

        List<EventoEmpleado> eventoEmpleados = new ArrayList<>();
        EventoEmpleado eventoEmpleado1 = new EventoEmpleado("id1", "idEvento1", "idEmpleado1", "tipo1", "nombre1", true, false, 0.5F);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado("id2", "idEvento2", "idEmpleado2", "tipo2", "nombre2", false, true, 1.5F);
        eventoEmpleados.add(eventoEmpleado1);
        eventoEmpleados.add(eventoEmpleado2);

        when(eventoService.getById(evento.getId())).thenReturn(evento);
        when(empleadoService.getByTipoAndFijo("Camarero/a", true)).thenReturn(empleadosFijos);
        when(empleadoService.getByTipoAndFijo("Camarero/a", false)).thenReturn(empleadosNoFijos);
        when(eventoEmpleadoService.getByIdEvento(evento.getId())).thenReturn(eventoEmpleados);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/empleados")
                .locale(new Locale("es", "ES"))
                .param("eventoId", evento.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getModificarVerTestUsuario() throws Exception {
        EventoEmpleado eventoEmpleado = new EventoEmpleado("id1", "idEvento1", "idEmpleado1", "tipo1", "nombre1", true, false, 0.5F);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/empleados/modificar")
                .param("eventoEmpleadoId", eventoEmpleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getModificarVerTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoEmpleadosUpdateModal.html");

        EventoEmpleado eventoEmpleado = new EventoEmpleado("id1", "idEvento1", "idEmpleado1", "tipo1", "nombre1", true, false, 0.5F);

        when(eventoEmpleadoService.getById(eventoEmpleado.getId())).thenReturn(eventoEmpleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/empleados/modificar")
                .locale(new Locale("es", "ES"))
                .param("eventoEmpleadoId", eventoEmpleado.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

}
