package com.example.eventos.invitado;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvitadoRepository  extends MongoRepository<Invitado, String> {
    List<Invitado> findByIdMesa(String idMesa);

    void deleteByIdMesa(String idMesa);

    void deleteByIdEvento(String idEvento);
}
