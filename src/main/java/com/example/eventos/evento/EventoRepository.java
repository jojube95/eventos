package com.example.eventos.evento;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    List<Evento> findEventoByTipo(String tipo);

    List<Evento> findAll();
}
