package com.example.eventos.eventoEmpleado;

import com.example.eventos.tipoEmpleado.TipoEmpleado;
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
        eventoEmpleado = new EventoEmpleado("id", "eventoId", "empleadoId", new TipoEmpleado("camarero"), "nombre", true, true, 0);
    }

    @Test
    void getByEventoIdTest() {
        eventoEmpleadoService.getByEventoId(eventoEmpleado.getEventoId());
        verify(eventoEmpleadoRepository, times(1)).findByEventoId(eventoEmpleado.getEventoId());
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
