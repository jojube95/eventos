package com.example.eventos.evento;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    Evento findEventoById(String id);

    List<Evento> findByFechaBefore(Date date);

    List<Evento> findAllByOrderByFechaAsc();

    List<Evento> findAllByFechaBetween(Date from, Date to);

    @Aggregation("{ '$group' : { '_id' : { $year: $fecha} } }")
    AggregationResults<org.bson.Document> findAllYears();
}
