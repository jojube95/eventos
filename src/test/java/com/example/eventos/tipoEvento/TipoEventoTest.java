package com.example.eventos.tipoEvento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoEventoTest {
    TipoEvento tipoEvento;

    @BeforeEach
    public void initEach(){
        tipoEvento = new TipoEvento("Boda");
    }

    @Test
    void constructorTest(){
        assertEquals("Boda", tipoEvento.getValue());
    }

    @Test
    void toStringTest(){
        assertEquals("TipoEvento{value='Boda'}", tipoEvento.toString());
    }
}
