package com.example.eventos.tipoEvento;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEventoRepository extends MongoRepository<TipoEvento, String> {
}
