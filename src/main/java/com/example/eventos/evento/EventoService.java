package com.example.eventos.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private  EventoRepository eventoRepository;

    public List<Evento> findEventosByTipo(String tipo){
        return eventoRepository.findEventoByTipo(tipo);
    }

    public List<Evento> getEventos(){
        return eventoRepository.findAll();
    }
}
