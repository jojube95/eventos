package com.example.eventos.invitado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        invitado = new Invitado("idEvento", "idMesa", "Pepe", "Mayor", "Descripcion");
    }

    @Test
    void findByMesaTest(){
        invitadoService.findByMesa(invitado.getIdMesa());
        verify(invitadoRepository, times(1)).findByIdMesa(invitado.getIdMesa());
    }

    @Test
    void saveTest(){
        invitadoService.save(invitado);
        verify(invitadoRepository, times(1)).save(invitado);
    }

    @Test
    void deleteTest(){
        invitadoService.delete(invitado);
        verify(invitadoRepository, times(1)).delete(invitado);
    }

    @Test
    void deleteInvitadosTest(){
        invitadoService.deleteInvitados(invitado.getIdMesa());
        verify(invitadoRepository, times(1)).deleteByIdMesa(invitado.getIdMesa());
    }
}
