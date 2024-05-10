package com.example.eventos.empleado;

import com.example.eventos.persona.Persona;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoRestController.class)
@Import(SecurityConfiguration.class)
class EmpleadoRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void deleteTestUsuario() throws Exception {
        Empleado empleado = new Empleado("id", new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true, true, true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/eliminarEmpleado")
                .with(csrf())
                .param("empleadoId", empleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(empleadoService, times(0)).delete(empleado);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void disableTestAdmin() throws Exception {
        Empleado empleado = new Empleado("id", new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true, true, true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        String expectedResponse = "{\"id\":\"id\",\"tipo\":{\"value\":\"camarero\"},\"persona\":{\"nombre\":\"nombre\",\"telefono\":\"telefono\",\"correo\":\"correo\"},\"fijo\":true,\"activo\":false,\"devantal\":true}";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/deshabilitarEmpleado")
                .with(csrf())
                .param("empleadoId", empleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        empleado.setActivo(false);

        verify(empleadoService, times(1)).save(empleado);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEmpleadosTest() throws Exception {
        Empleado empleado = new Empleado("id", new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true, true, true);

        when(empleadoService.getEmpleados()).thenReturn(List.of(empleado));

        String expectedResponse = "[{\"id\":\"id\",\"tipo\":{\"value\":\"camarero\"},\"persona\":{\"nombre\":\"nombre\",\"telefono\":\"telefono\",\"correo\":\"correo\"},\"fijo\":true,\"activo\":true,\"devantal\":true}]";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/empleados")
                .with(csrf());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        verify(empleadoService, times(1)).getEmpleados();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getEmpleadoTest() throws Exception {
        Empleado empleado = new Empleado("id", new TipoEmpleado("camarero"), new Persona("nombre", "telefono", "correo"), true, true, true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        String expectedResponse = "{\"id\":\"id\",\"tipo\":{\"value\":\"camarero\"},\"persona\":{\"nombre\":\"nombre\",\"telefono\":\"telefono\",\"correo\":\"correo\"},\"fijo\":true,\"activo\":true,\"devantal\":true}";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/empleado")
                .with(csrf())
                .param("id", empleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect((content().string(expectedResponse)));

        empleado.setActivo(false);

        verify(empleadoService, times(1)).getById(empleado.getId());
    }
}
