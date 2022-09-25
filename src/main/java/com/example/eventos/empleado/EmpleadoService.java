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

    public Empleado getById(String id) {
        return empleadoRepository.findEmpleadoById(id);
    }

    public List<Empleado> getByTipoAndFijo(String tipo, boolean fijo) {
        return empleadoRepository.findEmpleadoByTipoAndFijo(tipo, fijo);
    }

    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public void delete(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }
}
