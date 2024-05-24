package com.example.eventos.tipoPlato;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPlatoRepository extends MongoRepository<TipoPlato, String> {
}
