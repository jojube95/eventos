package com.example.eventos.empleado;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
    Empleado findEmpleadoById(String id);

    List<Empleado> findEmpleadoByTipo(String tipo);

    List<Empleado> findEmpleadoByTipoAndFijo(String tipo, boolean fijo);
}
