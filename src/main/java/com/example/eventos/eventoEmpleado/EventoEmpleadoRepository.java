package com.example.eventos.eventoEmpleado;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventoEmpleadoRepository extends MongoRepository<EventoEmpleado, String> {
    List<EventoEmpleado> findByIdEvento(String idEvento);

    List<EventoEmpleado> findByIdEmpleado(String idEmpleado);

    EventoEmpleado findEventoEmpleadoById(String id);
}
