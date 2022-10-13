package com.example.eventos.eventoEmpleado;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoEmpleadoService {

    private final EventoEmpleadoRepository eventoEmpleadoRepository;

    public EventoEmpleadoService(EventoEmpleadoRepository eventoEmpleadoRepository) {
        this.eventoEmpleadoRepository = eventoEmpleadoRepository;
    }

    public List<EventoEmpleado> getByEventoId(String eventoId) {
        return eventoEmpleadoRepository.findByEventoId(eventoId);
    }

    public EventoEmpleado getById(String id){
        return eventoEmpleadoRepository.findEventoEmpleadoById(id);
    }

    public EventoEmpleado save(EventoEmpleado eventoEmpleado) {
        return eventoEmpleadoRepository.save(eventoEmpleado);
    }

    public void delete(EventoEmpleado eventoEmpleado){
        eventoEmpleadoRepository.delete(eventoEmpleado);
    }
}
