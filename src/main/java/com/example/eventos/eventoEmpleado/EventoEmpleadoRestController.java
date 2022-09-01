package com.example.eventos.eventoEmpleado;

import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class EventoEmpleadoRestController {

    private final EmpleadoService empleadoService;
    private final EventoEmpleadoService eventoEmpleadoService;

    public EventoEmpleadoRestController(EmpleadoService empleadoService, EventoEmpleadoService eventoEmpleadoService) {
        this.empleadoService = empleadoService;
        this.eventoEmpleadoService = eventoEmpleadoService;
    }

    @PostMapping("/evento/empleados/anyadir")
    public EventoEmpleado add(@RequestParam("eventoId") String eventoId, @RequestParam("empleadoId") String empleadoId) throws IOException {
        Empleado empleado = empleadoService.getById(empleadoId);
        return eventoEmpleadoService.save(new EventoEmpleado(eventoId, empleadoId, empleado.getTipo(), empleado.getNombre(), empleado.isFijo(), false, 0.0F));
    }

    @PostMapping("/evento/empleados/eliminar")
    public EventoEmpleado delete(@RequestParam("eventoEmpleadoId") String eventoEmpleadoId) throws IOException {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        eventoEmpleadoService.delete(eventoEmpleado);
        return eventoEmpleado;
    }
    @PostMapping("/evento/empleados/modificar")
    public EventoEmpleado modificarEventoEmpleado(@RequestParam("eventoEmpleadoId") String eventoEmpleadoId, @RequestParam("confirmado") boolean confirmado, @RequestParam("horasExtras") float horasExtras, Model model) {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        eventoEmpleado.setConfirmado(confirmado);
        eventoEmpleado.setHorasExtras(horasExtras);

        eventoEmpleadoService.save(eventoEmpleado);
        return eventoEmpleado;
    }
}
