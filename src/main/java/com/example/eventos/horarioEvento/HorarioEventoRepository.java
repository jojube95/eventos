package com.example.eventos.horarioEvento;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioEventoRepository extends MongoRepository<HorarioEvento, String> {
}
