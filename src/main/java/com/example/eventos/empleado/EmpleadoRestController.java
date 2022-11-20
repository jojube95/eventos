package com.example.eventos.empleado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.eventos.config.Constants.EMPLEADO_ID;

@RestController
public class EmpleadoRestController {

    private final EmpleadoService empleadoService;

    public EmpleadoRestController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/eliminarEmpleado")
    public Empleado delete(@RequestParam(EMPLEADO_ID) String empleadoId) {
        Empleado empleado = empleadoService.getById(empleadoId);
        empleadoService.delete(empleado);
        return empleado;
    }
}
