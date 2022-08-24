package com.example.eventos.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    UsuarioService usuarioService;

    Usuario usuario;

    @BeforeEach
    public void initEach(){
        usuario = new Usuario("usuario", "password", "ROLE_USUARIO");
    }

    @Test
    void loadUserByUsernameTest() {
        when(usuarioRepository.findUsuarioByUsername(usuario.getUsername())).thenReturn(usuario);

        usuarioService.loadUserByUsername(usuario.getUsername());

        verify(usuarioRepository, times(1)).findUsuarioByUsername("usuario");
        verify(bCryptPasswordEncoder, times(1)).encode("password");
    }

    @Test
    void loadUserByUsernameDontFindedTest() {
        when(usuarioRepository.findUsuarioByUsername(usuario.getUsername())).thenReturn(null);

        Throwable exception = assertThrows(UsernameNotFoundException.class, () -> usuarioService.loadUserByUsername("usuario"));
        assertEquals("Username does not exist", exception.getMessage());
    }
}
