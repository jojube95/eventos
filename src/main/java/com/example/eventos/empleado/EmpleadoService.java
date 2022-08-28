package com.example.eventos.empleado;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> getEmpleados() {
        return empleadoRepository.findAll();
    }

    public List<Empleado> getByTipo(String tipo) {
        return empleadoRepository.findEventoByTipo(tipo);
    }

    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public void delete(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }
}
