package com.example.eventos.eventoEmpleado;

import com.example.eventos.evento.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoEmpleadoRepository extends MongoRepository<EventoEmpleado, String> {
    List<EventoEmpleado> findByIdEvento(String idEvento);

    EventoEmpleado findEventoEmpleadoById(String id);
}
