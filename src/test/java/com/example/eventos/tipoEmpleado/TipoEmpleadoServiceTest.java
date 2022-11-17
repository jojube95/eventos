package com.example.eventos.tipoEmpleado;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TipoEmpleadoServiceTest {

    @Mock
    TipoEmpleadoRepository tipoEmpleadoRepository;

    @InjectMocks
    TipoEmpleadoService tipoEmpleadoService;

    @Test
    void getTipoEmpleadosTest(){
        tipoEmpleadoService.getTipoEmpleados();
        verify(tipoEmpleadoRepository, times(1)).findAll();
    }
}
