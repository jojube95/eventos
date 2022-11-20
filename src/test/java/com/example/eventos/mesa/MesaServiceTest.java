package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoFactory;
import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.pdf.PdfCreator;
import com.example.eventos.personas.Personas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.example.eventos.config.Constants.INVITADO_TIPO_MAYOR;
import static com.example.eventos.config.Constants.INVITADO_TIPO_NINYO;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MesaServiceTest {
    @Mock
    MesaRepository mesaRepository;

    @Mock
    InvitadoRepository invitadoRepository;

    @Mock
    PdfCreator pdfCreator;

    @InjectMocks
    MesaService mesaService;

    Mesa mesa;

    @BeforeEach
    public void initEach(){
        mesa = new MesaReserva("id", "eventoId", new Personas(10, 1), 1, "descripcion", "Antonio", true);

    }

    @Test
    void findByEventoTest(){
        mesaService.findByEvento(mesa.getEventoId());
        verify(mesaRepository, times(1)).findByEventoId(mesa.getEventoId());
    }

    @Test
    void findByEventoOrderByNumeroTest(){
        mesaService.findByEventoOrderByNumero(mesa.getEventoId());
        verify(mesaRepository, times(1)).findByEventoIdOrderByNumeroAsc(mesa.getEventoId());
    }

    @Test
    void saveTest(){
        mesaService.save(mesa);
        verify(mesaRepository, times(1)).save(mesa);
    }

    @Test
    void deleteTest(){
        mesaService.delete(mesa);
        verify(mesaRepository, times(1)).delete(mesa);
        verify(invitadoRepository, times(1)).deleteByMesaId(mesa.getId());
    }

    @Test
    void deleteMesasTest(){
        mesaService.deleteMesas(mesa.getEventoId());
        verify(mesaRepository, times(1)).deleteByEventoId(mesa.getEventoId());
        verify(invitadoRepository, times(1)).deleteByEventoId(mesa.getEventoId());
    }

    @Test
    void generateInvitadosTest(){
        Mesa mesa = new Mesa("eventoId", new Personas(2, 1), 1, "descripcion");

        Invitado invitadoExpected1 = InvitadoFactory.crearInvitado(null, mesa.getEventoId(), mesa.getId(), "Invitado1", INVITADO_TIPO_MAYOR, "");
        Invitado invitadoExpected2 = InvitadoFactory.crearInvitado(null, mesa.getEventoId(), mesa.getId(), "Invitado2", INVITADO_TIPO_MAYOR, "");
        Invitado invitadoExpected3 = InvitadoFactory.crearInvitado(null, mesa.getEventoId(), mesa.getId(), INVITADO_TIPO_NINYO + "1", INVITADO_TIPO_NINYO, "");

        mesaService.generateInvitados(mesa);

        verify(invitadoRepository, times(1)).save(invitadoExpected1);
        verify(invitadoRepository, times(1)).save(invitadoExpected2);
        verify(invitadoRepository, times(1)).save(invitadoExpected3);
    }
}
