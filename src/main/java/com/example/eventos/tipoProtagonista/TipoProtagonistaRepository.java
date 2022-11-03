package com.example.eventos.tipoProtagonista;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProtagonistaRepository extends MongoRepository<TipoProtagonista, String> {
}
