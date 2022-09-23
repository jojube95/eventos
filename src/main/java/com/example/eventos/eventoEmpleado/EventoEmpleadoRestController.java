package com.example.eventos.eventoEmpleado;

import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.eventos.config.Constants.*;

@RestController
public class EventoEmpleadoRestController {

    private final EmpleadoService empleadoService;
    private final EventoEmpleadoService eventoEmpleadoService;

    public EventoEmpleadoRestController(EmpleadoService empleadoService, EventoEmpleadoService eventoEmpleadoService) {
        this.empleadoService = empleadoService;
        this.eventoEmpleadoService = eventoEmpleadoService;
    }

    @PostMapping("/evento/empleados/anyadir")
    public EventoEmpleado add(@RequestParam(EVENTO_ID) String eventoId, @RequestParam(EMPLEADO_ID) String empleadoId) {
        Empleado empleado = empleadoService.getById(empleadoId);
        return eventoEmpleadoService.save(new EventoEmpleado(eventoId, empleadoId, empleado.getTipo(), empleado.getNombre(), empleado.isFijo(), false, 0.0F));
    }

    @PostMapping("/evento/empleados/eliminar")
    public EventoEmpleado delete(@RequestParam(EVENTO_EMPELADO_ID) String eventoEmpleadoId) {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        eventoEmpleadoService.delete(eventoEmpleado);
        return eventoEmpleado;
    }
    @PostMapping("/evento/empleados/modificar")
    public EventoEmpleado modificarEventoEmpleado(@RequestParam(EVENTO_EMPELADO_ID) String eventoEmpleadoId, @RequestParam(EVENTO_EMPELADO_CONFIRMADO) boolean confirmado, @RequestParam(EVENTO_EMPELADO_HORAS_EXTRA) float horasExtras) {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        eventoEmpleado.setConfirmado(confirmado);
        eventoEmpleado.setHorasExtras(horasExtras);

        eventoEmpleadoService.save(eventoEmpleado);
        return eventoEmpleado;
    }
}
