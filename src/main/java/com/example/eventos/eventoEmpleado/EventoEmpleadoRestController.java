package com.example.eventos.eventoEmpleado;

import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.eventos.config.Constants.*;

@RestController
public class EventoEmpleadoRestController {

    private final EmpleadoService empleadoService;
    private final EventoEmpleadoService eventoEmpleadoService;
    private final EventoService eventoService;

    public EventoEmpleadoRestController(EmpleadoService empleadoService, EventoEmpleadoService eventoEmpleadoService, EventoService eventoService) {
        this.empleadoService = empleadoService;
        this.eventoEmpleadoService = eventoEmpleadoService;
        this.eventoService = eventoService;
    }

    @PostMapping("/evento/empleados/anyadir")
    public EventoEmpleado add(@RequestParam(EVENTO_ID) String eventoId, @RequestParam(EMPLEADO_ID) String empleadoId) {
        Evento evento = eventoService.getById(eventoId);
        Empleado empleado = empleadoService.getById(empleadoId);

        EventoEmpleado eventoEmpleado = new EventoEmpleado(evento, empleado, false, 0.0F);

        return eventoEmpleadoService.save(eventoEmpleado);
    }

    @PostMapping("/evento/empleados/eliminar")
    public EventoEmpleado delete(@RequestParam(EVENTO_EMPELADO_ID) String eventoEmpleadoId) {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        eventoEmpleadoService.delete(eventoEmpleado);
        return eventoEmpleado;
    }
    @PostMapping("/evento/empleados/modificar")
    public EventoEmpleado modificarEventoEmpleado(@RequestParam(EVENTO_EMPELADO_ID) String eventoEmpleadoId,
                                                  @RequestParam(EVENTO_EMPELADO_CONFIRMADO) boolean confirmado,
                                                  @RequestParam(EVENTO_EMPELADO_HORAS_EXTRA) double horasExtras) {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        eventoEmpleado.setConfirmado(confirmado);
        eventoEmpleado.setHorasExtras(horasExtras);

        eventoEmpleadoService.save(eventoEmpleado);
        return eventoEmpleado;
    }
}
