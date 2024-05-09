package com.example.eventos.tipoProtagonista;

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
class TipoProtagonistaRepositoryIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TipoProtagonistaRepository tipoProtagonistaRepository;

    @BeforeEach
    public void setUp(){
        TipoProtagonista tipoProtagonista1 = new TipoProtagonista("Novio");
        TipoProtagonista tipoProtagonista2 = new TipoProtagonista("Comuniante");

        mongoTemplate.insert(tipoProtagonista1);
        mongoTemplate.insert(tipoProtagonista2);
    }

    @Test
    void findAllTest(){
        TipoProtagonista tipoProtagonistaExpected1 = new TipoProtagonista("Novio");
        TipoProtagonista tipoProtagonistaExpected2 = new TipoProtagonista("Comuniante");

        List<TipoProtagonista> tipoProtagonistasExpected = new ArrayList<>();
        tipoProtagonistasExpected.add(tipoProtagonistaExpected1);
        tipoProtagonistasExpected.add(tipoProtagonistaExpected2);

        assertEquals(tipoProtagonistasExpected, tipoProtagonistaRepository.findAll());
    }

    @AfterEach
    public void cleanUpDatabase(){
        mongoTemplate.getDb().drop();
    }
}
