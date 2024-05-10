package com.example.eventos.empleado;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.example.eventos.config.Constants.EMPLEADO_ID;

@RestController
public class EmpleadoRestController {

    private final EmpleadoService empleadoService;

    public EmpleadoRestController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/deshabilitarEmpleado")
    public Empleado disable(@RequestParam(EMPLEADO_ID) String empleadoId) {
        Empleado empleado = empleadoService.getById(empleadoId);
        empleado.setActivo(false);
        empleadoService.save(empleado);
        return empleado;
    }

    @GetMapping("/api/empleados")
    public List<Empleado> getEmpleados(){
        return empleadoService.getEmpleados();
    }

    @GetMapping("/api/empleado")
    public Empleado getEmpleado(@RequestParam("id") String empleadoId){
        return empleadoService.getById(empleadoId);
    }
}
