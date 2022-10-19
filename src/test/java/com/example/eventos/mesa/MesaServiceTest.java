package com.example.eventos.mesa;

import com.example.eventos.invitado.InvitadoRepository;
import com.example.eventos.personas.Personas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MesaServiceTest {
    @Mock
    MesaRepository mesaRepository;

    @Mock
    InvitadoRepository invitadoRepository;

    @InjectMocks
    MesaService mesaService;

    Mesa mesa;

    /*
    @BeforeEach
    public void initEach(){
        mesa = new Mesa("eventoId", "Antonio", new Personas(10, 1), 1, true, "descripcion");
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

     */
}
