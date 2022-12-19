package com.example.eventos.empleado;

import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
    Empleado findEmpleadoById(String id);

    List<Empleado> findEmpleadoByActivo(boolean activo);

    List<Empleado> findEmpleadoByTipoAndActivo(TipoEmpleado tipo, boolean activo);

    List<Empleado> findEmpleadoByTipoAndFijoAndActivo(TipoEmpleado tipo, boolean fijo, boolean activo);
}
