package com.example.eventos.eventoEmpleado;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.parametros.Parametros;
import com.example.eventos.parametros.ParametrosService;
import com.example.eventos.persona.Persona;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
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

@WebMvcTest(EventoEmpleadoController.class)
@Import(SecurityConfiguration.class)
class EventoEmpleadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @MockBean
    private EventoEmpleadoService eventoEmpleadoService;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private ParametrosService parametrosService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getEventoEmpleadosTestUsuario() throws Exception {
        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/empleados")
                .param("eventoId", evento.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEventoEmpleadosTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoEmpleados.html");

        Evento evento = new Evento("id", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        List<Empleado> empleadosFijos = new ArrayList<>();
        List<Empleado> empleadosNoFijos = new ArrayList<>();
        Empleado empleadoFijo1 = new Empleado("id1", new TipoEmpleado("camarero"), new Persona("nombre1", "telefono1", "correo1"), true, true);
        Empleado empleadoFijo2 = new Empleado("id2", new TipoEmpleado("cocinero"), new Persona("nombre2", "telefono2", "correo2"), true, true);
        Empleado empleadoNoFijo1 = new Empleado("id3", new TipoEmpleado("camarero2"), new Persona("nombre3", "telefono3", "correo3"), false, true);
        Empleado empleadoNoFijo2 = new Empleado("id4", new TipoEmpleado("cocinero2"), new Persona("nombre4", "telefono4", "correo4"), false, true);
        empleadosFijos.add(empleadoFijo1);
        empleadosFijos.add(empleadoFijo2);
        empleadosNoFijos.add(empleadoNoFijo1);
        empleadosNoFijos.add(empleadoNoFijo2);

        List<EventoEmpleado> eventoEmpleados = new ArrayList<>();
        Evento evento1 = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Evento evento2 = new Evento("idEvento2", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true);
        Empleado empleado2 = new Empleado("idEmpleado2", new TipoEmpleado("cocinero"), new Persona("nombre2", "666777999", "correo2"), false, true);

        EventoEmpleado eventoEmpleado1 = new EventoEmpleado(evento1, empleado1, false, 0.5F);
        EventoEmpleado eventoEmpleado2 = new EventoEmpleado(evento2, empleado2, true, 1.5F);
        eventoEmpleados.add(eventoEmpleado1);
        eventoEmpleados.add(eventoEmpleado2);

        when(eventoService.getById(evento.getId())).thenReturn(evento);
        when(empleadoService.getByTipoAndFijo(new TipoEmpleado("camarero"), true)).thenReturn(empleadosFijos);
        when(empleadoService.getByTipoAndFijo(new TipoEmpleado("camarero"), false)).thenReturn(empleadosNoFijos);
        when(eventoEmpleadoService.getByEventoId(evento.getId())).thenReturn(eventoEmpleados);
        when(parametrosService.get()).thenReturn(new Parametros(20, 15, 0.3F, 15));

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
        Evento evento1 = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true);

        EventoEmpleado eventoEmpleado = new EventoEmpleado(evento1, empleado1, false, 0.5F);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/empleados/modificar")
                .param("eventoEmpleadoId", eventoEmpleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getModificarVerTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/eventoEmpleadosUpdateModal.html");

        Evento evento1 = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado1 = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre1", "666777888", "correo1"), true, true);

        EventoEmpleado eventoEmpleado = new EventoEmpleado("id", evento1, empleado1, false, 0.5F);

        when(eventoEmpleadoService.getById(eventoEmpleado.getId())).thenReturn(eventoEmpleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/evento/empleados/modificar")
                .locale(new Locale("es", "ES"))
                .param("eventoEmpleadoId", eventoEmpleado.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

}
