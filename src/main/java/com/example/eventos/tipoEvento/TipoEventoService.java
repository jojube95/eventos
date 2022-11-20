package com.example.eventos.tipoEvento;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoEventoService {
    private final TipoEventoRepository tipoEventoRepository;

    public TipoEventoService(TipoEventoRepository tipoEventoRepository) {
        this.tipoEventoRepository = tipoEventoRepository;
    }

    public List<TipoEvento> getTipoEventos(){
        return tipoEventoRepository.findAll();
    }
}
