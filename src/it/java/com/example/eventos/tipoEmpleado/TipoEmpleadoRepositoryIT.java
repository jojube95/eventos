package com.example.eventos.tipoEmpleado;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@TestPropertySource(locations = "classpath:application-integration.properties")
class TipoEmpleadoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TipoEmpleadoRepository TipoEmpleadoRepository;

    @BeforeEach
    public void setUp(){
        TipoEmpleado tipoEmpleado1 = new TipoEmpleado("Camarero");
        TipoEmpleado tipoEmpleado2 = new TipoEmpleado("Cocinero");

        mongoTemplate.insert(tipoEmpleado1);
        mongoTemplate.insert(tipoEmpleado2);
    }

    @Test
    void findAllTest(){
        TipoEmpleado tipoEmpleadoExpected1 = new TipoEmpleado("Camarero");
        TipoEmpleado tipoEmpleadoExpected2 = new TipoEmpleado("Cocinero");

        List<TipoEmpleado> tipoEmpleadosExpected = new ArrayList<>();
        tipoEmpleadosExpected.add(tipoEmpleadoExpected1);
        tipoEmpleadosExpected.add(tipoEmpleadoExpected2);

        assertEquals(tipoEmpleadosExpected, TipoEmpleadoRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
