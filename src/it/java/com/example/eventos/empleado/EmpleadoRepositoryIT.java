package com.example.eventos.empleado;

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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @BeforeEach
    public void setUp(){
        Empleado empleado1 = new Empleado("id1", "tipo1", "nombre1", "telefono1", true);
        Empleado empleado2 = new Empleado("id2", "tipo2", "nombre2", "telefono2", false);

        mongoTemplate.insert(empleado1);
        mongoTemplate.insert(empleado2);
    }

    @Test
    void findEmpleadoByIdTest(){
        Empleado empleadoExpected = new Empleado("id1", "tipo1", "nombre1", "telefono1", true);

        assertEquals(empleadoExpected, empleadoRepository.findEmpleadoById("id1"));
    }

    @Test
    void findEmpleadoByTipoTest(){
        List<Empleado> empleadosExpected = new ArrayList<>();
        Empleado empleadoExpected = new Empleado("id1", "tipo1", "nombre1", "telefono1", true);

        empleadosExpected.add(empleadoExpected);

        assertEquals(empleadosExpected, empleadoRepository.findEmpleadoByTipo("tipo1"));
    }

    @Test
    void findEmpleadoByTipoAndFijoTest(){
        List<Empleado> empleadosExpected = new ArrayList<>();
        Empleado empleadoExpected = new Empleado("id1", "tipo1", "nombre1", "telefono1", true);

        empleadosExpected.add(empleadoExpected);

        assertEquals(empleadosExpected, empleadoRepository.findEmpleadoByTipoAndFijo("tipo1", true));
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
