package com.example.eventos.empleado;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static com.example.eventos.config.Constants.*;

@Controller
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    private final EventoService eventoService;

    public EmpleadoController(EmpleadoService empleadoService, EventoService eventoService) {
        this.empleadoService = empleadoService;
        this.eventoService = eventoService;
    }

    @GetMapping("/empleados")
    public String empleados(Model model) {
        List<Empleado> empleados = empleadoService.getEmpleados();
        model.addAttribute(EMPLEADOS, empleados);
        return EMPLEADOS_PAGE;
    }

    @GetMapping("/empleadoAnyadir")
    public String empleadoAnyadir(Model model) {
        model.addAttribute(EMPLEADO, new Empleado());
        return EMPLEADO_ANYADIR_PAGE;
    }

    @PostMapping("/empleadoAnyadirUpdate")
    public String save(@ModelAttribute Empleado empleado) {
        empleadoService.save(empleado);
        return "redirect:/" + EMPLEADOS_PAGE;
    }

    @GetMapping("/empleadoUpdate")
    public String empleadoUpdate(@RequestParam(EMPLEADO_ID) String empleadoId, Model model) {
        Empleado empleado = empleadoService.getById(empleadoId);
        model.addAttribute(EMPLEADO, empleado);
        return EMPLEADO_UPDATE_PAGE;
    }

    @GetMapping("/empleadoHistorial")
    public String empleadoHistorial(@RequestParam(EMPLEADO_ID) String empleadoId, Model model) {
        List<Evento> eventos = eventoService.getByEmpleadoId(empleadoId);
        model.addAttribute(EVENTOS, eventos);
        return EMPLEADO_HISTORIAL_PAGE;
    }
}
