package com.example.eventos.tipoEmpleado;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoEmpleadoService {
    private final TipoEmpleadoRepository tipoEmpleadoRepository;

    public TipoEmpleadoService(TipoEmpleadoRepository tipoEmpleadoRepository) {
        this.tipoEmpleadoRepository = tipoEmpleadoRepository;
    }

    public List<TipoEmpleado> getTipoEmpleados(){
        return tipoEmpleadoRepository.findAll();
    }
}
