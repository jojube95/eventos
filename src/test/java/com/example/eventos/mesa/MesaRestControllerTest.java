package com.example.eventos.mesa;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoFactory;
import com.example.eventos.invitado.InvitadoService;
import com.example.eventos.personas.Personas;
import com.example.eventos.security.SecurityConfiguration;
import com.example.eventos.tipoEvento.TipoEvento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MesaRestController.class)
@Import(SecurityConfiguration.class)
class MesaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private MesaService mesaService;

    @MockBean
    private InvitadoService invitadoService;

    @MockBean
    private EventoService eventoService;

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void addTestUsuario() throws Exception {
        MesaReserva mesa = new MesaReserva("id", "eventoId", new Personas(3, 1), 2, "descripcion", "Pepe", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/add")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(mesaService, times(0)).save(mesa);
        verify(invitadoService, times(0)).save(InvitadoFactory.crearInvitado("id", mesa.getEventoId(), mesa.getId(), "Invitado1", "Mayor", ""));
        verify(invitadoService, times(0)).save(InvitadoFactory.crearInvitado("id", mesa.getEventoId(), mesa.getId(), "Invitado2", "Mayor", ""));
        verify(invitadoService, times(0)).save(InvitadoFactory.crearInvitado("id", mesa.getEventoId(), mesa.getId(), "Invitado3", "Mayor", ""));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void addTestAdmin() throws Exception {
        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MesaReserva mesa = new MesaReserva("id", "eventoId", new Personas(3, 1), 2, "descripcion", "Pepe", true);

        when(eventoService.getById(mesa.getEventoId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/add")
                .with(csrf())
                .param("eventoId", mesa.getEventoId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).save(mesa);
        verify(mesaService, times(1)).generateInvitados(mesa);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void addIndividualTestAdmin() throws Exception {
        Evento evento = new Evento("eventoId", new TipoEvento("eventoIndividual"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));

        MesaReserva mesa = new MesaReserva("id", "eventoId", new Personas(3, 1), 2, "descripcion", "Pepe", true);

        when(eventoService.getById(mesa.getEventoId())).thenReturn(evento);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/add")
                .with(csrf())
                .param("eventoId", mesa.getEventoId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).save(mesa);
        verify(mesaService, times(0)).generateInvitados(mesa);
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void deleteTestUsuario() throws Exception {
        MesaReserva mesa = new MesaReserva("id", "eventoId", new Personas(3, 1), 2, "descripcion", "Pepe", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/delete")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(mesaService, times(0)).delete(mesa);
        verify(invitadoService, times(0)).deleteInvitados(mesa.getId());
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void deleteTestAdmin() throws Exception {
        MesaReserva mesa = new MesaReserva("id", "eventoId", new Personas(3, 1), 2, "descripcion", "Pepe", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/delete")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).delete(mesa);
        verify(invitadoService, times(1)).deleteInvitados(mesa.getId());
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void updateTest() throws Exception {
        MesaReserva mesa = new MesaReserva("id", "eventoId", new Personas(10, 1), 2, "descripcion", "Pepe", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/update")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().is(403));

        verify(mesaService, times(0)).save(mesa);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void updateTestAdmin() throws Exception {
        MesaReserva mesa = new MesaReserva("id", "eventoId", new Personas(3, 1), 2, "descripcion", "Pepe", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/evento/mesas/update")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(mesa));

        this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk());

        verify(mesaService, times(1)).save(mesa);
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void importarMesaInvitadosFromExcelTest() throws Exception {
        Evento evento = new Evento("eventoId", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", new GregorianCalendar(2010, Calendar.FEBRUARY, 3).getTime(), "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Mesa mesaExcel1 = new Mesa("eventoId", new Personas(2, 1), 1, "Botellas");
        Mesa mesaExcel2 = new Mesa("eventoId", new Personas(1, 1), 2, "");
        Mesa mesaExcel3 = new Mesa("eventoId", new Personas(1, 1), 3, "Licores");
        MesaReserva mesa1 = new MesaReserva("mesaId1", "eventoId", new Personas(2, 1), 1, "Botellas", "", true);
        MesaReserva mesa2 = new MesaReserva("mesaId2", "eventoId", new Personas(1, 1), 2, "", "", true);
        MesaReserva mesa3 = new MesaReserva("mesaId3", "eventoId", new Personas(1, 1), 3, "Licores", "", true);

        List<Invitado> invitados1 = new ArrayList<>();
        List<Invitado> invitados2 = new ArrayList<>();
        List<Invitado> invitados3 = new ArrayList<>();
        Invitado invitado1 = InvitadoFactory.crearInvitado(null, "eventoId", "mesaId1", "Antonio", "Mayor", "");
        Invitado invitado2 = InvitadoFactory.crearInvitado(null, "eventoId", "mesaId1", "Pepe", "Mayor", "Intolerant");
        Invitado invitado3 = InvitadoFactory.crearInvitado(null, "eventoId", "mesaId1", "Amaia", "Niño", "Celiaca");
        invitados1.add(invitado1);
        invitados1.add(invitado2);
        invitados1.add(invitado3);
        Invitado invitado4 = InvitadoFactory.crearInvitado(null, "eventoId", "mesaId2", "Amaia", "Mayor", "");
        Invitado invitado5 = InvitadoFactory.crearInvitado(null, "eventoId", "mesaId2", "Pepe", "Niño", "Celiaco");
        invitados2.add(invitado4);
        invitados2.add(invitado5);
        Invitado invitado6 = InvitadoFactory.crearInvitado(null, "eventoId", "mesaId3", "Jose", "Mayor", "");
        Invitado invitado7 = InvitadoFactory.crearInvitado(null, "eventoId", "mesaId3", "Pepa", "Niño", "");
        invitados3.add(invitado6);
        invitados3.add(invitado7);

        when(eventoService.getById(evento.getId())).thenReturn(evento);
        when(mesaService.save(mesaExcel1)).thenReturn(mesa1);
        when(mesaService.save(mesaExcel2)).thenReturn(mesa2);
        when(mesaService.save(mesaExcel3)).thenReturn(mesa3);

        evento.setDistribucion(new Distribucion(""));

        FileInputStream fis = new FileInputStream("src/test/resources/files/listadoTest.xlsx");
        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.multipart("/evento/mesas/uploadExcel")
                .file(multipartFile)
                .with(csrf())
                .param("eventoId", evento.getId());


        mockMvc.perform(mockRequest).andExpect(status().isOk());

        verify(eventoService, times(1)).update(evento);
        verify(mesaService, times(1)).deleteMesas(evento.getId());
        verify(mesaService, times(1)).save(mesaExcel1);
        verify(mesaService, times(1)).save(mesaExcel2);
        verify(mesaService, times(1)).save(mesaExcel3);
        verify(invitadoService, times(1)).saveMany(invitados1);
        verify(invitadoService, times(1)).saveMany(invitados2);
        verify(invitadoService, times(1)).saveMany(invitados3);
    }
}
