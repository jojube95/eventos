package com.example.eventos.invitado;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvitadoRestController.class)
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
    void addUpdateTest() throws Exception {
        Invitado invitado = new Invitado("idEvento", "idMesa", "Antonio", "Vegano");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/invitados/addUpdate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(invitado));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(invitadoService, times(1)).save(invitado);
    }

    @Test
    void deleteTest() throws Exception {
        Invitado invitado = new Invitado("idEvento", "idMesa", "Antonio", "Vegano");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/invitados/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(invitado));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(invitadoService, times(1)).delete(invitado);
    }
}
