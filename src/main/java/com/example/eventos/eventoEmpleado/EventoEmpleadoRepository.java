package com.example.eventos.eventoEmpleado;

import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventoEmpleadoRepository extends MongoRepository<EventoEmpleado, String> {
    List<EventoEmpleado> findByEventoId(String eventoId);

    List<EventoEmpleado> findEventoEmpleadosByEventoIdAndTipoEmpleado(String eventoId, TipoEmpleado tipoEmpleado);

    List<EventoEmpleado> findByEmpleadoId(String empleadoId);

    EventoEmpleado findEventoEmpleadoById(String id);
}
