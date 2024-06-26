package com.example.eventos.empleado;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import com.example.eventos.tipoEmpleado.TipoEmpleadoService;
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
    private final TipoEmpleadoService tipoEmpleadoService;

    public EmpleadoController(EmpleadoService empleadoService, EventoService eventoService, TipoEmpleadoService tipoEmpleadoService) {
        this.empleadoService = empleadoService;
        this.eventoService = eventoService;
        this.tipoEmpleadoService = tipoEmpleadoService;
    }

    @GetMapping("/empleados")
    public String empleados(Model model) {
        List<Empleado> empleadosCamareros = empleadoService.getByTipo(new TipoEmpleado(EMPLEADO_TIPO_CAMARERO));
        List<Empleado> empleadosCocineros = empleadoService.getByTipo(new TipoEmpleado(EMPLEADO_TIPO_COCINERO));
        model.addAttribute(ATTRIBUTE_EMPLEADOS_CAMAREROS, empleadosCamareros);
        model.addAttribute(ATTRIBUTE_EMPLEADOS_COCINEROS, empleadosCocineros);
        return EMPLEADOS_PAGE;
    }

    @GetMapping("/empleadoAnyadir")
    public String empleadoAnyadir(Model model) {
        model.addAttribute(EMPLEADO, new Empleado());
        model.addAttribute(ATTRIBUTE_TIPOS_EMPLEADO, tipoEmpleadoService.getTipoEmpleados());
        return EMPLEADO_ANYADIR_PAGE;
    }

    @PostMapping("/empleadoAnyadirUpdate")
    public String save(@ModelAttribute Empleado empleado) {
        empleado.setActivo(true);
        empleadoService.save(empleado);
        return "redirect:/" + EMPLEADOS_PAGE;
    }

    @GetMapping("/empleadoUpdate")
    public String empleadoUpdate(@RequestParam(EMPLEADO_ID) String empleadoId, Model model) {
        Empleado empleado = empleadoService.getById(empleadoId);
        model.addAttribute(EMPLEADO, empleado);
        model.addAttribute(ATTRIBUTE_TIPOS_EMPLEADO, tipoEmpleadoService.getTipoEmpleados());
        return EMPLEADO_UPDATE_PAGE;
    }

    @GetMapping("/empleadoHistorial")
    public String empleadoHistorial(@RequestParam(EMPLEADO_ID) String empleadoId, Model model) {
        List<Evento> eventos = eventoService.getByEmpleadoId(empleadoId);
        Empleado empleado = empleadoService.getById(empleadoId);
        model.addAttribute(EVENTOS, eventos);
        model.addAttribute(EMPLEADO, empleado);
        return EMPLEADO_HISTORIAL_PAGE;
    }
}
