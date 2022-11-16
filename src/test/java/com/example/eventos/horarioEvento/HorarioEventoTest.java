package com.example.eventos.horarioEvento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorarioEventoTest {
    HorarioEvento horarioEvento;

    @BeforeEach
    public void initEach(){
        horarioEvento = new HorarioEvento("Cena");
    }

    @Test
    void constructorTest(){
        assertEquals("Cena", horarioEvento.getValue());
    }

    @Test
    void toStringTest(){
        assertEquals("HorarioEvento{value='Cena'}", horarioEvento.toString());
    }
}
