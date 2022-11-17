package com.example.eventos.tipoEvento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TipoEventoServiceTest {

    @Mock
    TipoEventoRepository tipoEventoRepository;

    @InjectMocks
    TipoEventoService tipoEventoService;

    @Test
    void getTipoEventosTest(){
        tipoEventoService.getTipoEventos();
        verify(tipoEventoRepository, times(1)).findAll();
    }
}
