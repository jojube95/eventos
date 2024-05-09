package com.example.eventos.parametros;

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
class ParametrosRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ParametrosRepository parametrosRepository;

    @BeforeEach
    public void setUp(){
        Parametros parametros = new Parametros(1.1F, 1.2F, 1.3F, 1.4F);

        mongoTemplate.insert(parametros);
    }

    @Test
    void findAllTest(){
        Parametros parametroExpected = new Parametros(1.1F, 1.2F, 1.3F, 1.4F);
        List<Parametros> parametrosExpected = new ArrayList<>();
        parametrosExpected.add(parametroExpected);

        assertEquals(parametrosExpected, parametrosRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
