package com.example.eventos.tipoProtagonista;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TipoProtagonistaServiceTest {

    @Mock
    TipoProtagonistaRepository tipoProtagonistaRepository;

    @InjectMocks
    TipoProtagonistaService tipoProtagonistaService;

    @Test
    void getTipoProtagonistasTest(){
        tipoProtagonistaService.getTipoProtagonistas();
        verify(tipoProtagonistaRepository, times(1)).findAll();
    }
}
