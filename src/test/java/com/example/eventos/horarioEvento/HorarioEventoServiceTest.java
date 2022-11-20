package com.example.eventos.horarioEvento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HorarioEventoServiceTest {

    @Mock
    HorarioEventoRepository horarioEventoRepository;

    @InjectMocks
    HorarioEventoService horarioEventoService;

    @Test
    void getHorarioEventosTest(){
        horarioEventoService.getHorarioEventos();
        verify(horarioEventoRepository, times(1)).findAll();
    }
}
