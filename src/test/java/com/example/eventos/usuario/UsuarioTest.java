package com.example.eventos.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    Usuario usuario1;
    Usuario usuario2;
    Usuario usuario3;

    @BeforeEach
    public void initEach(){
        usuario1 = new Usuario("username", "password", "rol");
        usuario2 = new Usuario("username", "password", "rol");
        usuario3 = new Usuario("id", "username3", "password3", "rol3");
    }

    @Test
    void constructorWithoutIdTest(){
        assertEquals("username", usuario1.getUsername());
        assertEquals("password", usuario1.getPassword());
        assertEquals("rol", usuario1.getRol());
    }

    @Test
    void constructorWithIdTest(){
        assertEquals("id", usuario3.getId());
        assertEquals("username3", usuario3.getUsername());
        assertEquals("password3", usuario3.getPassword());
        assertEquals("rol3", usuario3.getRol());
    }

    @Test
    void equalsTrue(){
        assertEquals(usuario1, usuario2);
    }

    @Test
    void equalsFalse(){
        assertNotEquals(usuario1, usuario3);
    }
}
