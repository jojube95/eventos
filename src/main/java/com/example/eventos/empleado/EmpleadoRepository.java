package com.example.eventos.empleado;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
    Empleado findEventoById(String id);

    List<Empleado> findEventoByTipo(String tipo);

    List<Empleado> findEventoByTipoAndFijo(String tipo, boolean fijo);
}
