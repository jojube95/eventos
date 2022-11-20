package com.example.eventos.parametros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParametrosServiceTest {
    @Mock
    ParametrosRepository parametrosRepository;

    @InjectMocks
    ParametrosService parametrosService;

    @Test
    void getParametrosTest(){
        List<Parametros> parametros = new ArrayList<>();
        parametros.add(new Parametros(1.1F, 1.2F, 1.3F, 1.4F));
        when(parametrosRepository.findAll()).thenReturn(parametros);

        parametrosService.get();
        verify(parametrosRepository, times(1)).findAll();
    }
}
