package com.example.eventos.eventoEmpleado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoEmpleadoServiceTest {

    @Mock
    EventoEmpleadoRepository eventoEmpleadoRepository;

    @InjectMocks
    EventoEmpleadoService eventoEmpleadoService;

    EventoEmpleado eventoEmpleado;

    @BeforeEach
    public void initEach(){
        eventoEmpleado = new EventoEmpleado("id", "idEvento", "idEmpleado", "tipo", "nombre", true, true, 0);
    }

    @Test
    void getByIdEventoTest() {
        eventoEmpleadoService.getByIdEvento(eventoEmpleado.getIdEvento());
        verify(eventoEmpleadoRepository, times(1)).findByIdEvento(eventoEmpleado.getIdEvento());
    }

    @Test
    void getByIdEmpleadoTest() {
        eventoEmpleadoService.getByIdEmpleado(eventoEmpleado.getIdEmpleado());
        verify(eventoEmpleadoRepository, times(1)).findByIdEmpleado(eventoEmpleado.getIdEmpleado());
    }

    @Test
    void getByIdTest() {
        eventoEmpleadoService.getById(eventoEmpleado.getId());
        verify(eventoEmpleadoRepository, times(1)).findEventoEmpleadoById(eventoEmpleado.getId());
    }

    @Test
    void saveTest() {
        eventoEmpleadoService.save(eventoEmpleado);
        verify(eventoEmpleadoRepository, times(1)).save(eventoEmpleado);
    }

    @Test
    void deleteTest() {
        eventoEmpleadoService.delete(eventoEmpleado);
        verify(eventoEmpleadoRepository, times(1)).delete(eventoEmpleado);
    }
}
