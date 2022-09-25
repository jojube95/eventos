package com.example.eventos.eventoEmpleado;

import com.example.eventos.empleado.Empleado;
import com.example.eventos.empleado.EmpleadoService;
import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static com.example.eventos.config.Constants.*;

@Controller
public class EventoEmpleadoController {
    private final EventoEmpleadoService eventoEmpleadoService;
    private final EventoService eventoService;
    private final EmpleadoService empleadoService;

    public EventoEmpleadoController(EventoEmpleadoService eventoEmpleadoService, EventoService eventoService, EmpleadoService empleadoService) {
        this.eventoEmpleadoService = eventoEmpleadoService;
        this.eventoService = eventoService;
        this.empleadoService = empleadoService;
    }

    @GetMapping("/evento/empleados")
    public String eventoEmpleados(@RequestParam(EVENTO_EMPELADO_EVENTO_ID) String eventoId, Model model) {
        Evento evento = eventoService.getById(eventoId);
        List<Empleado> empleadosFijos = empleadoService.getByTipoAndFijo("Camarero/a", true);
        List<Empleado> empleadosNoFijos = empleadoService.getByTipoAndFijo("Camarero/a", false);
        List<EventoEmpleado> eventoEmpleados = eventoEmpleadoService.getByIdEvento(eventoId);

        model.addAttribute(EVENTO_EMPELADOS, eventoEmpleados);
        model.addAttribute("empleadosFijos", empleadosFijos);
        model.addAttribute("empleadosNoFijos", empleadosNoFijos);
        model.addAttribute(EVENTO_EMPELADO_EVENTO_ID, eventoId);
        model.addAttribute(EVENTO_PERSONAS, evento.getPersonas());
        model.addAttribute("camarerosRecomendados", evento.getCamarerosRecomendados());
        return EVENTO_EMPELADOS_PAGE;
    }

    @GetMapping("/evento/empleados/modificar")
    public String modificarVer(@RequestParam(EVENTO_EMPELADO_ID) String eventoEmpleadoId, Model model) {
        EventoEmpleado eventoEmpleado = eventoEmpleadoService.getById(eventoEmpleadoId);
        model.addAttribute(eventoEmpleado);
        return "fragments/eventoEmpleadoModificarModal :: modalContents";
    }
}
