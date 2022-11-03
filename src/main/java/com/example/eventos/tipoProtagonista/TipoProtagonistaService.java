package com.example.eventos.tipoProtagonista;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProtagonistaService {
    private final TipoProtagonistaRepository tipoProtagonistaRepository;

    public TipoProtagonistaService(TipoProtagonistaRepository tipoProtagonistaRepository) {
        this.tipoProtagonistaRepository = tipoProtagonistaRepository;
    }

    public List<TipoProtagonista> getTipoProtagonistas(){
        return tipoProtagonistaRepository.findAll();
    }
}
