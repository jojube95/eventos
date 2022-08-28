package com.example.eventos.empleado;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
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

    @GetMapping("/anyadirEmpleado")
    public String anyadirEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "anyadirEmpleado";
    }

    @PostMapping("/anyadirEmpleado")
    public String save(@ModelAttribute Empleado empleado) throws IOException {
        empleadoService.save(empleado);
        return "redirect:/empleados";
    }
}
