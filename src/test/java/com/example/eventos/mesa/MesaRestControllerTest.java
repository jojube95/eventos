package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoService;
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

@WebMvcTest(MesaRestController.class)
public class MesaRestControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private MesaService mesaService;

    @MockBean
    private InvitadoService invitadoService;

    @Test
    public void addTest() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 3, 2, true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).save(mesa);
        verify(invitadoService, times(1)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado1", ""));
        verify(invitadoService, times(1)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado2", ""));
        verify(invitadoService, times(1)).save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado3", ""));
    }

    @Test
    public void deleteTest() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 10, 2, true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).delete(mesa);
        verify(invitadoService, times(1)).deleteInvitados(mesa.getId());
    }

    @Test
    public void updateTest() throws Exception {
        Mesa mesa = new Mesa("idEvento", "Pepe", 10, 2, true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).save(mesa);
    }
}
