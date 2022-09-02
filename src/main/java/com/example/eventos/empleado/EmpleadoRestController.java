package com.example.eventos.empleado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpleadoRestController {

    private final EmpleadoService empleadoService;

    public EmpleadoRestController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/eliminarEmpleado")
    public Empleado delete(@RequestParam("empleadoId") String empleadoId) {
        Empleado empleado = empleadoService.getById(empleadoId);
        empleadoService.delete(empleado);
        return empleado;
    }
}
