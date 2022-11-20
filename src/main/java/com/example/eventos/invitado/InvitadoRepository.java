package com.example.eventos.invitado;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitadoRepository  extends MongoRepository<Invitado, String> {
    List<Invitado> findByMesaId(String mesaId);

    void deleteByMesaId(String mesaId);

    void deleteByEventoId(String eventoId);
}
