package com.example.eventos.horarioEvento;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HorarioEventoService {
    private final HorarioEventoRepository horarioEventoRepository;

    public HorarioEventoService(HorarioEventoRepository horarioEventoRepository) {
        this.horarioEventoRepository = horarioEventoRepository;
    }

    public List<HorarioEvento> getHorarioEventos(){
        return horarioEventoRepository.findAll();
    }
}
