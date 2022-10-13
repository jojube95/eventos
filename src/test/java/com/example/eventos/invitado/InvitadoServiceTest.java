package com.example.eventos.invitado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InvitadoServiceTest {
    @Mock
    InvitadoRepository invitadoRepository;

    @InjectMocks
    InvitadoService invitadoService;

    Invitado invitado;

    @BeforeEach
    public void initEach(){
        invitado = new Invitado("eventoId", "mesaId", "Pepe", "Mayor", "Descripcion");
    }

    @Test
    void findByMesaTest(){
        invitadoService.findByMesa(invitado.getMesaId());
        verify(invitadoRepository, times(1)).findByMesaId(invitado.getMesaId());
    }

    @Test
    void saveTest(){
        invitadoService.save(invitado);
        verify(invitadoRepository, times(1)).save(invitado);
    }

    @Test
    void saveManyTest(){
        List<Invitado> invitados = new ArrayList<>();
        invitados.add(invitado);

        invitadoService.saveMany(invitados);
        verify(invitadoRepository, times(1)).saveAll(invitados);
    }

    @Test
    void deleteTest(){
        invitadoService.delete(invitado);
        verify(invitadoRepository, times(1)).delete(invitado);
    }

    @Test
    void deleteInvitadosTest(){
        invitadoService.deleteInvitados(invitado.getMesaId());
        verify(invitadoRepository, times(1)).deleteByMesaId(invitado.getMesaId());
    }
}
