package com.example.eventos.empleado;

import com.example.eventos.evento.EventoService;
import com.example.eventos.eventoEmpleado.EventoEmpleadoService;
import com.example.eventos.security.SecurityConfiguration;
import com.example.utilities.TestUtilities;
import org.hamcrest.CoreMatchers;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoController.class)
@Import(SecurityConfiguration.class)
class EmpleadoControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @MockBean
    private EventoEmpleadoService eventoEmpleadoService;

    @MockBean
    private EventoService eventoService;

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void empleadosTestUsuario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/empleados");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void empleadosTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/empleados.html");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/empleados")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getAnyadirEmpleadoTestUsuario() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/empleadoAnyadir");

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getAnyadirEmpleadoTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/empleadoAnyadir.html");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/empleadoAnyadir")
                .locale(new Locale("es", "ES"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void postUpdateAnyadirEmpleadoTestUsuario() throws Exception {
        Empleado empleado = new Empleado("id", "tipo", "nombre", "telefono", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/empleadoAnyadirUpdate")
                .with(csrf())
                .flashAttr("empleado", empleado);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(empleadoService, times(0)).save(empleado);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void postUpdateAnyadirEmpleadoTestAdmin() throws Exception {
        Empleado empleado = new Empleado("id", "tipo", "nombre", "telefono", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/empleadoAnyadirUpdate")
                .with(csrf())
                .flashAttr("empleado", empleado);

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/empleados"));

        verify(empleadoService, times(1)).save(empleado);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getUpdateEmpleadoTestUsuario() throws Exception {
        Empleado empleado = new Empleado("id", "tipo", "nombre", "telefono", true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/empleadoUpdate")
                .param("empleadoId", empleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void getUpdateEmpleadoTestAdmin() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/empleadoUpdate.html");

        Empleado empleado = new Empleado("id", "tipo", "nombre", "telefono", true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/empleadoUpdate")
                .locale(new Locale("es", "ES"))
                .param("empleadoId", empleado.getId());

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void getHistorialEmpleadoTestUsuario() throws Exception {
        Empleado empleado = new Empleado("id", "tipo", "nombre", "telefono", true);

        when(empleadoService.getById(empleado.getId())).thenReturn(empleado);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/empleadoHistorial")
                .param("empleadoId", empleado.getId());

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));
    }
}
