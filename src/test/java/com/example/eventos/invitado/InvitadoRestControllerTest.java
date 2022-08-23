package com.example.eventos.invitado;

import com.example.eventos.security.SecurityConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvitadoRestController.class)
@Import(SecurityConfiguration.class)
class InvitadoRestControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private InvitadoService invitadoService;

    @Test
    @WithMockUser(username="admin",roles={"USUARIO"})
    void addUpdateTest() throws Exception {
        Invitado invitado = new Invitado("idEvento", "idMesa", "Antonio", "Vegano");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/invitados/addUpdate")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(invitado));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(invitadoService, times(1)).save(invitado);
    }

    @Test
    @WithMockUser(username="admin",roles={"USUARIO"})
    void deleteTest() throws Exception {
        Invitado invitado = new Invitado("idEvento", "idMesa", "Antonio", "Vegano");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/invitados/delete")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(invitado));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(invitadoService, times(1)).delete(invitado);
    }
}
