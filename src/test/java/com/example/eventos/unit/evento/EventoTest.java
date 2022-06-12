package com.example.eventos.unit.evento;

import com.example.eventos.evento.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventoTest {
    Evento evento;

    @BeforeEach
    public void initEach(){
        evento = new Evento("Boda", "Cena", 150);
    }

    @Test
    public void constructorTest(){
        assertEquals("Boda", evento.getTipo());
        assertEquals("Cena", evento.getHorario());
        assertEquals(150, evento.getPersonas());
    }

    @Test
    public void toStringTest(){
        assertEquals("Boda - Cena - 150", evento.toString());
    }
}
