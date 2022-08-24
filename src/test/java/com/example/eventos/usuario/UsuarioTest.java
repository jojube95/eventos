package com.example.eventos.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
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

    @Test
    void equalsToString(){
        assertEquals("Usuario{id='id', nombre='username3', rol='rol3'}", usuario3.toString());
    }

    @Test
    void getAuthoritiesTest() {
        List<GrantedAuthority> expectedList = new ArrayList<>();
        expectedList.add(new SimpleGrantedAuthority("rol"));

        assertEquals(expectedList, usuario1.getAuthorities());
    }
}
