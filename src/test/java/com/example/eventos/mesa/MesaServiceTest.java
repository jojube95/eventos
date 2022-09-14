package com.example.eventos.mesa;

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

    @InjectMocks
    MesaService mesaService;

    Mesa mesa;

    @BeforeEach
    public void initEach(){
        mesa = new Mesa("idEvento", "Antonio", 10, 1, true, "descripcion");
    }

    @Test
    void findByEventoTest(){
        mesaService.findByEvento(mesa.getIdEvento());
        verify(mesaRepository, times(1)).findByIdEvento(mesa.getIdEvento());
    }

    @Test
    void findByEventoOrderByNumeroTest(){
        mesaService.findByEventoOrderByNumero(mesa.getIdEvento());
        verify(mesaRepository, times(1)).findByIdEventoOrderByNumeroAsc(mesa.getIdEvento());
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
    }

    @Test
    void deleteMesasTest(){
        mesaService.deleteMesas(mesa.getIdEvento());
        verify(mesaRepository, times(1)).deleteByIdEvento(mesa.getIdEvento());
    }
}
