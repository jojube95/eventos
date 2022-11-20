package com.example.eventos.empleado;

import com.example.eventos.persona.Persona;
import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class EmpleadoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @BeforeEach
    public void setUp(){
        Empleado empleado1 = new Empleado("id1", new TipoEmpleado("camarero"), new Persona("nombre1", "telefono1", "correo1"), true, true);
        Empleado empleado2 = new Empleado("id2", new TipoEmpleado("cocinero"), new Persona("nombre2", "telefono2", "correo2"), false, true);
        Empleado empleado3 = new Empleado("id3", new TipoEmpleado("camarero"), new Persona("nombre3", "telefono3", "correo3"), false, false);

        mongoTemplate.insert(empleado1);
        mongoTemplate.insert(empleado2);
        mongoTemplate.insert(empleado3);
    }

    @Test
    void findEmpleadoByIdTest(){
        Empleado empleadoExpected = new Empleado("id1", new TipoEmpleado("camarero"), new Persona("nombre1", "telefono1", "correo1"), true, true);

        assertEquals(empleadoExpected, empleadoRepository.findEmpleadoById("id1"));
    }

    @Test
    void findEmpleadoByTipoTest(){
        List<Empleado> empleadosExpected = new ArrayList<>();
        Empleado empleadoExpected = new Empleado("id1", new TipoEmpleado("camarero"), new Persona("nombre1", "telefono1", "correo1"),true, true);

        empleadosExpected.add(empleadoExpected);

        assertEquals(empleadosExpected, empleadoRepository.findEmpleadoByTipoAndActivo(new TipoEmpleado("camarero"), true));
    }

    @Test
    void findEmpleadoByTipoAndFijoAndActivoTest(){
        List<Empleado> empleadosExpected = new ArrayList<>();
        Empleado empleadoExpected = new Empleado("id1", new TipoEmpleado("camarero"), new Persona("nombre1", "telefono1", "correo1"), true, true);

        empleadosExpected.add(empleadoExpected);

        assertEquals(empleadosExpected, empleadoRepository.findEmpleadoByTipoAndFijoAndActivo(new TipoEmpleado("camarero"), true, true));
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
