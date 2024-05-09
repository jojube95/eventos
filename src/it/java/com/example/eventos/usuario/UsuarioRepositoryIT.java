package com.example.eventos.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@TestPropertySource(locations = "classpath:application-integration.properties")
class UsuarioRepositoryIT {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp(){
        Usuario usuario1 = new Usuario("id1", "admin", "admin", "ROLE_ADMIN");
        Usuario usuario2 = new Usuario("id2", "usuario", "usuario", "ROLE_USUARIO");
        mongoTemplate.insert(usuario1);
        mongoTemplate.insert(usuario2);
    }

    @Test
    void findUsuarioByUsernameTest(){
        Usuario usuarioExpected = new Usuario("id1", "admin", "admin", "ROLE_ADMIN");

        assertEquals(usuarioExpected, usuarioRepository.findUsuarioByUsername("admin"));
    }
}
