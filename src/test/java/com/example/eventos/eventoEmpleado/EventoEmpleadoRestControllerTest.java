package com.example.eventos.eventoEmpleado;

import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import com.example.eventos.evento.Evento;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import com.example.eventos.tipoEvento.TipoEvento;
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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventoEmpleadoRestController.class)
@Import(SecurityConfiguration.class)
class EventoEmpleadoRestControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @MockBean
    private EventoEmpleadoService eventoEmpleadoService;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void addTestUsuario() throws Exception {
        Evento evento = new Evento("id2", new TipoEvento("boda"), new HorarioEvento("cena"), new Personas(150, 10), "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Empleado empleado = new Empleado("id", new TipoEmpleado("camarero"), "nombre", "telefono", true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/empleados/anyadir")
                .with(csrf())
                .param("eventoId", evento.getId())
                .param("empleadoId", empleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoEmpleadoService, times(0)).save(new EventoEmpleado(evento.getId(), empleado.getId(), empleado.getTipo(), empleado.getNombre(), empleado.isFijo(), false, 0.0F));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void addTestAdmin() throws Exception {
        Evento evento = new Evento("id2", new TipoEvento("boda"), new HorarioEvento("cena"), new Personas(150, 10), "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Empleado empleado = new Empleado("id", new TipoEmpleado("camarero"), "nombre", "telefono", true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/empleados/anyadir")
                .with(csrf())
                .param("eventoId", evento.getId())
                .param("empleadoId", empleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(eventoEmpleadoService, times(1)).save(new EventoEmpleado(evento.getId(), empleado.getId(), empleado.getTipo(), empleado.getNombre(), empleado.isFijo(), false, 0.0F));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void deleteTestUsuario() throws Exception {
        EventoEmpleado eventoEmpleado = new EventoEmpleado("id", "eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);

        when(eventoEmpleadoService.getById(eventoEmpleado.getId())).thenReturn(eventoEmpleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/empleados/eliminar")
                .with(csrf())
                .param("eventoEmpleadoId", eventoEmpleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoEmpleadoService, times(0)).delete(eventoEmpleado);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void deleteTestAdmin() throws Exception {
        EventoEmpleado eventoEmpleado = new EventoEmpleado("id", "eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);

        when(eventoEmpleadoService.getById(eventoEmpleado.getId())).thenReturn(eventoEmpleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/empleados/eliminar")
                .with(csrf())
                .param("eventoEmpleadoId", eventoEmpleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(eventoEmpleadoService, times(1)).delete(eventoEmpleado);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void modificarEventoEmpleadoTestUsuario() throws Exception {
        EventoEmpleado eventoEmpleado = new EventoEmpleado("id", "eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);

        when(eventoEmpleadoService.getById(eventoEmpleado.getId())).thenReturn(eventoEmpleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/empleados/modificar")
                .with(csrf())
                .param("eventoEmpleadoId", eventoEmpleado.getId())
                .param("confirmado", "false")
                .param("horasExtras", "0.5");

        eventoEmpleado.setConfirmado(false);
        eventoEmpleado.setHorasExtras(0.5F);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(eventoEmpleadoService, times(0)).save(eventoEmpleado);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void modificarEventoEmpleadoTestAdmin() throws Exception {
        EventoEmpleado eventoEmpleado = new EventoEmpleado("id", "eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);

        when(eventoEmpleadoService.getById(eventoEmpleado.getId())).thenReturn(eventoEmpleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/empleados/modificar")
                .with(csrf())
                .param("eventoEmpleadoId", eventoEmpleado.getId())
                .param("confirmado", "false")
                .param("horasExtras", "0.5");

        eventoEmpleado.setConfirmado(false);
        eventoEmpleado.setHorasExtras(0.5F);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(eventoEmpleadoService, times(1)).save(eventoEmpleado);
    }
}
