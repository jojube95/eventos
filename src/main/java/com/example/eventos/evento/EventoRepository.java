package com.example.eventos.evento;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    List<Evento> findEventoByTipo(String tipo);

    Evento findEventoById(String id);

    List<Evento> findAll();

    Evento insert(Evento evento);
}
