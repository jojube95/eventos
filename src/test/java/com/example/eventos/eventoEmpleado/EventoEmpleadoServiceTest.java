package com.example.eventos.eventoEmpleado;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.empleado.Empleado;
import com.example.eventos.evento.Evento;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.persona.Persona;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import com.example.eventos.tipoEvento.TipoEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoEmpleadoServiceTest {

    @Mock
    EventoEmpleadoRepository eventoEmpleadoRepository;

    @InjectMocks
    EventoEmpleadoService eventoEmpleadoService;

    EventoEmpleado eventoEmpleado;

    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();

        Evento evento = new Evento("idEvento1", new TipoEvento("comunion"), new HorarioEvento("comida"), new Personas(50, 15), "Olleria", fecha, "descripcion", 80, 15, true, new ArrayList<>(), "Comunión-Comida", "Sala1", new Distribucion("Distribucion"));
        Empleado empleado = new Empleado("idEmpleado1", new TipoEmpleado("camarero"), new Persona("nombre", "666777888", "correo"), true, true, true);

        eventoEmpleado = new EventoEmpleado(evento, empleado, empleado.getTipo(), true, 0);
    }

    @Test
    void getByEventoIdTest() {
        eventoEmpleadoService.getByEventoId(eventoEmpleado.getEvento().getId());
        verify(eventoEmpleadoRepository, times(1)).findByEventoId(eventoEmpleado.getEvento().getId());
    }

    @Test
    void getByIdTest() {
        eventoEmpleadoService.getById(eventoEmpleado.getId());
        verify(eventoEmpleadoRepository, times(1)).findEventoEmpleadoById(eventoEmpleado.getId());
    }

    @Test
    void getByEventoIdAndTipoEmpleadoTest() {
        eventoEmpleadoService.getByEventoIdAndTipoEmpleado(eventoEmpleado.getId(), eventoEmpleado.getTipoEmpleado());
        verify(eventoEmpleadoRepository, times(1)).findEventoEmpleadosByEventoIdAndTipoEmpleado(eventoEmpleado.getId(), eventoEmpleado.getTipoEmpleado());
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
