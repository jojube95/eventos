package com.example.eventos.mesa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository  extends MongoRepository<Mesa, String> {
    List<Mesa> findByIdEvento(String idEvento);

    void deleteByIdEvento(String idEvento);

    List<Mesa> findByIdEventoOrderByNumeroAsc(String idEvento);
}
