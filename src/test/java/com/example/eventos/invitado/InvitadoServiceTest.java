package com.example.eventos.invitado;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoRepository;
import com.example.eventos.evento.EventoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InvitadoServiceTest {
    @Mock
    InvitadoRepository invitadoRepository;

    @InjectMocks
    InvitadoService invitadoService;

    Invitado invitado;

    @BeforeEach
    public void initEach(){
        invitado = new Invitado("idEvento", "idMesa", "Pepe", "Descripcion");
    }

    @Test
    public void findByMesaTest(){
        invitadoService.findByMesa(invitado.getIdMesa());
        verify(invitadoRepository, times(1)).findByIdMesa(invitado.getIdMesa());
    }

    @Test
    public void saveTest(){
        invitadoService.save(invitado);
        verify(invitadoRepository, times(1)).save(invitado);
    }

    @Test
    public void deleteTest(){
        invitadoService.delete(invitado);
        verify(invitadoRepository, times(1)).delete(invitado);
    }

    @Test
    public void deleteInvitadosTest(){
        invitadoService.deleteInvitados(invitado.getIdMesa());
        verify(invitadoRepository, times(1)).deleteByIdMesa(invitado.getIdMesa());
    }

}
