package com.example.eventos.parametros;

import org.springframework.stereotype.Service;

@Service
public class ParametrosService {

    private final ParametrosRepository parametrosRepository;

    public ParametrosService(ParametrosRepository parametrosRepository) {
        this.parametrosRepository = parametrosRepository;
    }

    public Parametros get() {
        return parametrosRepository.findAll().get(0);
    }
}
