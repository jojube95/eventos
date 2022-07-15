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

    public Evento getById(String id){
        return eventoRepository.findEventoById(id);
    }

    public Evento save(Evento evento){
        return eventoRepository.save(evento);
    }

    public void delete(Evento evento){
        eventoRepository.delete(evento);
    }
}
