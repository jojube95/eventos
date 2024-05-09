package com.example.eventos.tipoEvento;

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
class TipoEventoRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TipoEventoRepository tipoEventoRepository;

    @BeforeEach
    public void setUp(){
        TipoEvento tipoEvento1 = new TipoEvento("Boda");
        TipoEvento tipoEvento2 = new TipoEvento("Comunion");

        mongoTemplate.insert(tipoEvento1);
        mongoTemplate.insert(tipoEvento2);
    }

    @Test
    void findAllTest(){
        TipoEvento tipoEventoExpected1 = new TipoEvento("Boda");
        TipoEvento tipoEventoExpected2 = new TipoEvento("Comunion");

        List<TipoEvento> tipoEventoExpected = new ArrayList<>();
        tipoEventoExpected.add(tipoEventoExpected1);
        tipoEventoExpected.add(tipoEventoExpected2);

        assertEquals(tipoEventoExpected, tipoEventoRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
