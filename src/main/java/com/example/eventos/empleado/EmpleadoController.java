package com.example.eventos.empleado;

import com.example.eventos.evento.Evento;
import com.example.eventos.evento.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/empleados")
    public String empleados(Model model) {
        List<Empleado> empleados = empleadoService.getEmpleados();
        model.addAttribute("empleados", empleados);
        return "empleados";
    }
}
