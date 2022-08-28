package com.example.eventos.empleado;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
    List<Empleado> findEventoByTipo(String tipo);
}
