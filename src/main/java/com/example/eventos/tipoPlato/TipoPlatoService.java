package com.example.eventos.tipoPlato;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoPlatoService {
    private TipoPlatoRepository tipoPlatoRepository;

    public TipoPlatoService(TipoPlatoRepository tipoPlatoRepository) {
        this.tipoPlatoRepository = tipoPlatoRepository;
    }

    public List<TipoPlato> getTipoPlatos(){
        return tipoPlatoRepository.findAll();
    }
}
